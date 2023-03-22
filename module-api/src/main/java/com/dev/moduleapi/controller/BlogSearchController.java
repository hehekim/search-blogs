package com.dev.moduleapi.controller;

import com.dev.moduleapi.dto.request.BlogSearchRequest;
import com.dev.moduleapi.dto.response.Response;
import com.dev.moduleclient.dto.response.BlogSearchResponse;
import com.dev.moduleapi.service.BlogSearchApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogSearchController {

    private final BlogSearchApiService blogService;

    @PostMapping
    public Response<BlogSearchResponse> searchBlogs(@RequestBody @Valid BlogSearchRequest body) {
        log.info("Search blog by keyword. request data = {}", body.toString());
        return Response.success(blogService.searchBlogsByKeyword(body));
    }
}
