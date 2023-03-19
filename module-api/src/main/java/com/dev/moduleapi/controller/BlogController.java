package com.dev.moduleapi.controller;

import com.dev.moduleapi.dto.BlogRequest;
import com.dev.moduleapi.service.BlogService;
import com.dev.moduleclient.dto.BlogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/blogs")
    public ResponseEntity searchBlogs(@RequestParam String query,
                                      @RequestParam(required = false) String sort,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false, defaultValue = "10") Integer size) {

        BlogResponse result = blogService.searchBlogsByKeyword(BlogRequest.of(query, sort, page, size));
        return ResponseEntity.ok(result);
    }


}
