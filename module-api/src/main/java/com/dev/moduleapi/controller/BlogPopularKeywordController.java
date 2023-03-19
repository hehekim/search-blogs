package com.dev.moduleapi.controller;

import com.dev.moduleapi.dto.response.BlogPopularKeywordResponse;
import com.dev.moduleapi.dto.response.Response;
import com.dev.moduledomain.repository.BlogPopularKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BlogPopularKeywordController {
    private final BlogPopularKeywordRepository popularKeywordRepository;

    @GetMapping("/popular-keywords")
    public Response<List<BlogPopularKeywordResponse>> getTenPopularKeywords() {
        return Response.success(popularKeywordRepository.findTop10ByOrderBySearchCountDesc().stream()
                .map(BlogPopularKeywordResponse::from)
                .collect(Collectors.toList()));
    }
}
