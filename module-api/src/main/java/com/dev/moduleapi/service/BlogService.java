package com.dev.moduleapi.service;

import com.dev.moduleapi.dto.BlogRequest;
import com.dev.moduleapi.event.BlogPopularKeywordEvent;
import com.dev.moduleclient.client.KakaBlogOpenFeign;
import com.dev.moduleclient.client.NaverBlogOpenFeign;
import com.dev.moduleclient.dto.BlogResponse;
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


    public BlogResponse searchBlogsByKeyword(BlogRequest request) {
        try{
            BlogResponse response = searchBlogsByKaKao(request);

            publisher.publishEvent(
                    BlogPopularKeywordEvent.from(request.getQuery())
            );

            return response;
        } catch (Exception e) {
            return searchNaverByNaver(request);
        }
    }

    private BlogResponse searchNaverByNaver(BlogRequest request) {
        return naverBlogFeign.call(
                createURI(naverBlogUrl),
                naverClientId,
                naverClientSecret,
                request.getQuery(),
                request.getSort(),
                request.getPage(),
                request.getSize()
        );
    }

    private BlogResponse searchBlogsByKaKao(BlogRequest request) {
        return kakaoBlogFeign.call(
                createURI(kakaoBlogUrl),
                kakaoClientKey,
                request.getQuery(),
                request.getSort(),
                request.getPage(),
                request.getSize()
        );
    }

    private URI createURI(String uri) {
        return URI.create(uri);
    }
}
