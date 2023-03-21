package com.dev.moduleapi.service;

import com.dev.moduleapi.dto.request.BlogSearchRequest;
import com.dev.moduleapi.exception.ErrorCode;
import com.dev.moduleapi.exception.SearchApplicationException;
import com.dev.moduleclient.client.SearchClientFactory;
import com.dev.moduleclient.client.SearchClientType;
import com.dev.moduleclient.dto.response.BlogResponse;
import com.dev.moduleclient.dto.response.BlogSearchResponse;
import com.dev.moduledomain.service.PopularKeywordEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BlogSearchApiService {
    private final PopularKeywordEventService popularKeywordEventService;
    private final SearchClientFactory clientFactory;

    public BlogSearchResponse searchBlogsByKeyword(BlogSearchRequest request) {
        popularKeywordEventService.saveBlogPopularKeyword(request.getQuery());

        try{
            return searchByKeyword(SearchClientType.KAKAO_BLOG_SEARCH, request);
        } catch (Exception e) {
            return searchByKeyword(SearchClientType.NAVER_BLOG_SEARCH, request);
        }
    }

    private BlogSearchResponse searchByKeyword(SearchClientType type, BlogSearchRequest request) {
        BlogResponse response = clientFactory.getImplementationByType(type)
                .getResult()
                .call(request);
        BlogSearchResponse result = BlogSearchResponse.of(type, request, response);
        isExistsBlogSearchResponse(result);
        return result;
    }

    private void isExistsBlogSearchResponse(BlogSearchResponse result) {
        if (Objects.isNull(result)) {
            throw new SearchApplicationException(ErrorCode.SEARCH_TYPE_NOT_FOUND);
        }
    }
}
