package com.dev.moduleclient.client;

import com.dev.moduleclient.dto.KakaoBlogResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "KakaoOpenFeign", url = "USE_DYNAMIC_URI")
public interface KakaoOpenFeign {

    @GetMapping
    KakaoBlogResponse call(
            URI uri,
            @RequestHeader String Authorization,
            @RequestParam String query,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size);
}
