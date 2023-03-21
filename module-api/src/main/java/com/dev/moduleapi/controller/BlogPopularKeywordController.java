package com.dev.moduleapi.controller;

import com.dev.moduleapi.dto.response.BlogPopularKeywordResponse;
import com.dev.moduleapi.dto.response.Response;
import com.dev.moduleapi.service.BlogPopularKeywordApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/popular-keywords")
public class BlogPopularKeywordController {
    private final BlogPopularKeywordApiService popularKeywordService;

    @GetMapping
    public Response<List<BlogPopularKeywordResponse>> getTenPopularKeywords() {
        return Response.success(popularKeywordService.getTenPopularKeywords());
    }
}
