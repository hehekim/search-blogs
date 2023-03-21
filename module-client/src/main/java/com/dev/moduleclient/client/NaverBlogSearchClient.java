package com.dev.moduleclient.client;

import com.dev.moduleclient.dto.request.BlogRequest;
import com.dev.moduleclient.dto.response.BlogResponse;
import com.dev.moduleclient.dto.response.ClientResponse;
import com.dev.moduleclient.dto.response.NaverBlogResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
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
    public ClientResponse<BlogResponse> call(BlogRequest request) {
        try {
            log.info("Request to Naver client. request data = {}", request.toString());
            return ClientResponse.success(
                    restTemplate.exchange(
                            createURI(request),
                            HttpMethod.GET,
                            setHttpEntity(),
                            NaverBlogResponse.class
                    ).getBody()
            );
        } catch (HttpClientErrorException e) {
            log.error("[HttpClientErrorException] Exception occurred in NaverClient. keyword={}, status={}, cause={}", request.getQuery(), e.getStatusCode(), e.getResponseBodyAsString());
            return ClientResponse.failed(e);
        } catch (Exception e) {
            log.error("[Exception] Exception occurred in NaverClient. keyword={}, status={}, cause={}", request.getQuery(), NestedExceptionUtils.getMostSpecificCause(e).getCause(), NestedExceptionUtils.getMostSpecificCause(e));
            return ClientResponse.failed(e);
        }
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
                .queryParam(PAGE, setRequestPage(request.getPage()))
                .queryParam(SIZE, setRequestSize(request.getSize()))
                .build()
                .encode()
                .toUri();
    }

    @Override
    public Integer setRequestPage(Integer page) {
        return page > 1000 ? 1000 : page;
    }

    @Override
    public Integer setRequestSize(Integer size) {
        return size > 100 ? 100 : size;
    }
}