package com.dev.moduleapi.controller;

import com.dev.moduleapi.dto.request.BlogRequest;
import com.dev.moduleapi.dto.response.Response;
import com.dev.moduleapi.dto.response.SearchBlogResponse;
import com.dev.moduleapi.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/blogs")
    public Response<SearchBlogResponse> searchBlogs(@RequestParam String query,
                                                    @RequestParam(required = false) String sort,
                                                    @RequestParam(required = false) Integer page,
                                                    @RequestParam(required = false, defaultValue = "10") Integer size) {
        return Response.success(blogService.searchBlogsByKeyword(BlogRequest.of(query, sort, page, size)));
    }
}
