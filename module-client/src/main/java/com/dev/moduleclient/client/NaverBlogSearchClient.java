package com.dev.moduleclient.client;

import com.dev.moduleclient.dto.request.BlogRequest;
import com.dev.moduleclient.dto.response.BlogResponse;
import com.dev.moduleclient.dto.response.NaverBlogResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Component
public class NaverBlogSearchClient implements SearchClient<BlogRequest, BlogResponse> {
    public static final String X_NAVER_CLIENT_ID = "X-Naver-Client-Id";
    public static final String X_NAVER_CLIENT_SECRET = "X-Naver-Client-Secret";
    public static final String QUERY = "query";
    public static final String SORT = "sort";
    public static final String PAGE = "start";
    public static final String SIZE = "display";
    @Value("${naver.client-id}")
    private String naverClientId;
    @Value("${naver.client-secret}")
    private String naverClientSecret;
    @Value("${naver.url.blog}")
    private String naverBlogUrl;
    private final RestTemplate restTemplate;

    @Override
    public boolean isAvailableType(SearchClientType type) {
        return type == SearchClientType.NAVER_BLOG_SEARCH;
    }

    @Override
    public NaverBlogResponse call(BlogRequest request) {
        return restTemplate.exchange(
                createURI(request),
                HttpMethod.GET,
                setHttpEntity(),
                NaverBlogResponse.class
        ).getBody();
    }

    @Override
    public HttpEntity<String> setHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(X_NAVER_CLIENT_ID, naverClientId);
        httpHeaders.add(X_NAVER_CLIENT_SECRET, naverClientSecret);
        return new HttpEntity<>(httpHeaders);
    }

    @Override
    public URI createURI(BlogRequest request) {
        return UriComponentsBuilder.fromHttpUrl(naverBlogUrl)
                .queryParam(QUERY, request.getQuery())
                .queryParam(SORT, request.getSort().getNaverSort())
                .queryParam(PAGE, request.getPage())
                .queryParam(SIZE, request.getSize())
                .build()
                .encode()
                .toUri();
    }
}