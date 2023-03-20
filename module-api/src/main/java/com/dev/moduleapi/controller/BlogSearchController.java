package com.dev.moduleapi.controller;

import com.dev.moduleapi.dto.request.BlogSearchRequest;
import com.dev.moduleapi.dto.response.Response;
import com.dev.moduleclient.dto.response.BlogSearchResponse;
import com.dev.moduleapi.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogSearchController {

    private final BlogSearchService blogService;

    @PostMapping
    public Response<BlogSearchResponse> searchBlogs(@RequestBody @Valid BlogSearchRequest body) {
        return Response.success(blogService.searchBlogsByKeyword(body));
    }
}
