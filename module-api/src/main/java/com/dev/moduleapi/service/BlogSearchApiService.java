package com.dev.moduleapi.service;

import com.dev.moduleapi.dto.request.BlogSearchRequest;
import com.dev.moduleapi.exception.ErrorCode;
import com.dev.moduleapi.exception.ExceptionHandler;
import com.dev.moduleclient.client.SearchClient;
import com.dev.moduleclient.client.SearchClientFactory;
import com.dev.moduleclient.client.SearchClientType;
import com.dev.moduleclient.dto.request.BlogRequest;
import com.dev.moduleclient.dto.response.BlogResponse;
import com.dev.moduleclient.dto.response.BlogSearchResponse;
import com.dev.moduleclient.dto.response.ClientResponse;
import com.dev.moduledomain.service.PopularKeywordEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogSearchApiService {
    private final PopularKeywordEventService popularKeywordEventService;
    private final SearchClientFactory clientFactory;

    public BlogSearchResponse searchBlogsByKeyword(BlogSearchRequest request) {
        popularKeywordEventService.saveBlogPopularKeyword(request.getQuery());

        try{
            throw new Exception();
//            return callExternalClientBlogByKeyword(SearchClientType.KAKAO_BLOG_SEARCH, request);
        } catch (Exception e) {
            return callExternalClientBlogByKeyword(SearchClientType.NAVER_BLOG_SEARCH, request);
        }
    }

    public BlogSearchResponse callExternalClientBlogByKeyword(SearchClientType type, BlogSearchRequest request) {
        ClientResponse<SearchClient<BlogRequest, BlogResponse>> searchClientType = clientFactory.getImplementationByType(type);
        ExceptionHandler.checkException(searchClientType, ErrorCode.SEARCH_TYPE_NOT_FOUND);

        SearchClient<BlogRequest, BlogResponse> searchClient = searchClientType.getResult();
        ClientResponse<BlogResponse> response = searchClient.call(request);
        ExceptionHandler.checkException(response, ErrorCode.EXTERNAL_REQUEST_FAILED);

        return BlogSearchResponse.of(type, request, response.getResult());
    }
}
