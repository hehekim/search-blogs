package com.dev.moduleapi.service;

import com.dev.moduleapi.dto.BlogRequest;
import com.dev.moduleclient.client.KakaoOpenFeign;
import com.dev.moduleclient.dto.KakaoBlogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class BlogService {

    // TODO 네이버 API 와 같이 사용할 수 있도록 변경
    private final KakaoOpenFeign feign;


    public KakaoBlogResponse searchBlogs(BlogRequest request) {
        return feign.call(
                URI.create("https://dapi.kakao.com/v2/search/blog"),
                "KakaoAK 296f48e506c53091f5b24d4899867572",
                request.getQuery(),
                request.getSort(),
                request.getPage(),
                request.getSize()
        );
    }
}
