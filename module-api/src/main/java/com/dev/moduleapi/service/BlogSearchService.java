package com.dev.moduleapi.service;

import com.dev.moduleapi.dto.request.BlogSearchRequest;
import com.dev.moduleapi.dto.response.BlogSearchResponse;
import com.dev.moduleapi.event.BlogPopularKeywordEvent;
import com.dev.moduleclient.client.KakaoBlogSearchClient;
import com.dev.moduleclient.client.NaverBlogSearchClient;
import com.dev.moduleclient.dto.response.KakaoBlogResponse;
import com.dev.moduleclient.dto.response.NaverBlogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogSearchService {
    private final ApplicationEventPublisher publisher;
    private final KakaoBlogSearchClient kakaoClient;
    private final NaverBlogSearchClient naverClient;

    public BlogSearchResponse searchBlogsByKeyword(BlogSearchRequest request) {
        try{
            publisher.publishEvent(BlogPopularKeywordEvent.from(request.getQuery()));
            return searchBlogsByKaKao(request);
        } catch (Exception e) {
            return searchNaverByKeyword(request);
        }
    }

    private BlogSearchResponse searchNaverByKeyword(BlogSearchRequest request) {
        NaverBlogResponse response = naverClient.call(request);
        return BlogSearchResponse.of(request, response);
    }

    private BlogSearchResponse searchBlogsByKaKao(BlogSearchRequest request) {
        KakaoBlogResponse response = kakaoClient.call(request);
        return BlogSearchResponse.of(request, response);
    }
}
