package com.dev.moduleclient.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchClientType {
    KAKAO_BLOG_SEARCH("카카오 블로그 검색"),
    NAVER_BLOG_SEARCH("네이버 블로그 검색");

    private final String description;
}
