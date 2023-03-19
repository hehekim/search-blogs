package com.dev.moduleapi.service;

import com.dev.moduleapi.dto.request.BlogRequest;
import com.dev.moduleapi.dto.response.SearchBlogResponse;
import com.dev.moduleapi.event.BlogPopularKeywordEvent;
import com.dev.moduleclient.client.KakaBlogOpenFeign;
import com.dev.moduleclient.client.NaverBlogOpenFeign;
import com.dev.moduleclient.dto.response.KakaoBlogResponse;
import com.dev.moduleclient.dto.response.NaverBlogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class BlogService {
    @Value("${kakao.client-key}")
    private String kakaoClientKey;
    @Value("${kakao.url.blog}")
    private String kakaoBlogUrl;
    @Value("${naver.client-id}")
    private String naverClientId;
    @Value("${naver.client-secret}")
    private String naverClientSecret;
    @Value("${naver.url.blog}")
    private String naverBlogUrl;

    // TODO 네이버 API 와 같이 사용할 수 있도록 변경
    private final KakaBlogOpenFeign kakaoBlogFeign;
    private final NaverBlogOpenFeign naverBlogFeign;
    private final ApplicationEventPublisher publisher;


    public SearchBlogResponse searchBlogsByKeyword(BlogRequest request) {
        try{
            publisher.publishEvent(BlogPopularKeywordEvent.from(request.getQuery()));
            return searchBlogsByKaKao(request);
        } catch (Exception e) {
            return searchNaverByKeyword(request);
        }
    }

    private SearchBlogResponse searchNaverByKeyword(BlogRequest request) {
        NaverBlogResponse response = naverBlogFeign.call(
                createURI(naverBlogUrl),
                naverClientId,
                naverClientSecret,
                request.getQuery(),
                request.getSort(),
                request.getPage(),
                request.getSize()
        );
        return SearchBlogResponse.of(response, request);
    }

    private SearchBlogResponse searchBlogsByKaKao(BlogRequest request) {
        KakaoBlogResponse response = kakaoBlogFeign.call(
                createURI(kakaoBlogUrl),
                kakaoClientKey,
                request.getQuery(),
                request.getSort(),
                request.getPage(),
                request.getSize()
        );
        return SearchBlogResponse.of(response, request);
    }

    private URI createURI(String uri) {
        return URI.create(uri);
    }
}
