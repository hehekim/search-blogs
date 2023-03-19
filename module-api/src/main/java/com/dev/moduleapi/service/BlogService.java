package com.dev.moduleapi.service;

import com.dev.moduleapi.dto.BlogRequest;
import com.dev.moduleapi.event.BlogPopularKeywordEvent;
import com.dev.moduleclient.client.KakaBlogOpenFeign;
import com.dev.moduleclient.client.NaverBlogOpenFeign;
import com.dev.moduleclient.dto.BlogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class BlogService {

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
                URI.create("https://openapi.naver.com/v1/search/blog.json"),
                "luHBgaxTWHCjO3J7etF3",
                "ghxAsk28Gh",
                request.getQuery(),
                request.getSort(),
                request.getPage(),
                request.getSize()
        );
    }

    private BlogResponse searchBlogsByKaKao(BlogRequest request) {
        return kakaoBlogFeign.call(
                URI.create("https://dapi.kakao.com/v2/search/blog"),
                "KakaoAK 296f48e506c53091f5b24d4899867572",
                request.getQuery(),
                request.getSort(),
                request.getPage(),
                request.getSize()
        );
    }
}
