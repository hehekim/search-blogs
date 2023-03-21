package com.dev.moduleclient.client;

import com.dev.moduleclient.dto.request.BlogRequest;
import com.dev.moduleclient.dto.response.BlogResponse;
import com.dev.moduleclient.dto.response.ClientResponse;
import com.dev.moduleclient.dto.response.KakaoBlogResponse;
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
public class KakaoBlogSearchClient implements SearchClient<BlogRequest, BlogResponse> {
    public static final String QUERY = "query";
    public static final String SORT = "sort";
    public static final String PAGE = "page";
    public static final String SIZE = "size";
    @Value("${kakao.client-key}")
    private String kakaoClientKey;
    @Value("${kakao.url.blog}")
    private String kakaoBlogUrl;
    private final RestTemplate restTemplate;

    @Override
    public boolean isAvailableType(SearchClientType type) {
        return type == SearchClientType.KAKAO_BLOG_SEARCH;
    }

    @Override
    public ClientResponse<BlogResponse> call(BlogRequest request) {
        try {
            log.info("Request to Kakao client. request data = {}", request.toString());
            return ClientResponse.success(
                    restTemplate.exchange(
                            createURI(request),
                            HttpMethod.GET,
                            setHttpEntity(),
                            KakaoBlogResponse.class
                    ).getBody()
            );
        } catch (HttpClientErrorException e) {
            log.error("[HttpClientErrorException] Exception occurred in KakaoClient. keyword={}, status={}, cause={}", request.getQuery(), e.getStatusCode(), e.getResponseBodyAsString());
            return ClientResponse.failed(e);
        } catch (Exception e) {
            log.error("[Exception] Exception occurred in KakaoClient. keyword={}, status={}, cause={}", request.getQuery(), NestedExceptionUtils.getMostSpecificCause(e).getCause(), e.getCause().getMessage());
            return ClientResponse.failed(e);
        }
    }

    @Override
    public HttpEntity<String> setHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, kakaoClientKey);
        return new HttpEntity<>(httpHeaders);
    }

    @Override
    public URI createURI(BlogRequest request) {
        return UriComponentsBuilder.fromHttpUrl(kakaoBlogUrl)
                .queryParam(QUERY, request.getQuery())
                .queryParam(SORT, request.getSort().getKakaoSort())
                .queryParam(PAGE, setRequestPage(request.getPage()))
                .queryParam(SIZE, setRequestSize(request.getSize()))
                .build()
                .encode()
                .toUri();
    }

    @Override
    public Integer setRequestPage(Integer page) {
        return page > 50 ? 50 : page;
    }

    @Override
    public Integer setRequestSize(Integer size) {
        return size > 50 ? 50 : size ;
    }
}
