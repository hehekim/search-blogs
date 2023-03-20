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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Component
public class KakaoBlogSearchClient implements SearchClient<BlogRequest, BlogResponse> {
    @Value("${kakao.client-key}")
    private String kakaoClientKey;
    @Value("${kakao.url.blog}")
    private String kakaoBlogUrl;
    private final RestTemplate restTemplate;

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
        httpHeaders.add(org.springframework.http.HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, kakaoClientKey);
        return new HttpEntity<>(httpHeaders);
    }

    @Override
    public URI createURI(BlogRequest request) {
        return UriComponentsBuilder.fromHttpUrl(kakaoBlogUrl)
                .queryParam("query", request.getQuery())
                .queryParam("sort", request.getSort().getKakaoSort())
                .queryParam("page", request.getPage())
                .queryParam("size", request.getSize())
                .build()
                .encode()
                .toUri();
    }
}
