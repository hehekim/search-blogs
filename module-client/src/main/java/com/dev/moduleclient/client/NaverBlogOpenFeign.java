package com.dev.moduleclient.client;

import com.dev.moduleclient.dto.response.NaverBlogResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "NaverBlogOpenFeign", url = "USE_DYNAMIC_URI")
public interface NaverBlogOpenFeign {

    @GetMapping
    NaverBlogResponse call(
            URI uri,
            @RequestHeader("X-Naver-Client-Id") String NaverClientId,
            @RequestHeader("X-Naver-Client-Secret") String NaverClientSecret,
            @RequestParam String query,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Integer start,
            @RequestParam(required = false) Integer display);
}
