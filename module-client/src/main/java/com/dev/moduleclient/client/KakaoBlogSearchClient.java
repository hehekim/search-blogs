package com.dev.moduleclient.client;

import com.dev.moduleclient.dto.request.BlogRequest;
import com.dev.moduleclient.dto.response.BlogResponse;
import com.dev.moduleclient.dto.response.KakaoBlogResponse;
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
    public KakaoBlogResponse call(BlogRequest request) {
        return restTemplate.exchange(
                createURI(request),
                HttpMethod.GET,
                setHttpEntity(),
                KakaoBlogResponse.class
        ).getBody();
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
                .queryParam(PAGE, request.getPage())
                .queryParam(SIZE, request.getSize())
                .build()
                .encode()
                .toUri();
    }
}
