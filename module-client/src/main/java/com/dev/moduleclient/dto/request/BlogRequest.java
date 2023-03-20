package com.dev.moduleclient.dto.request;

import lombok.Getter;

@Getter
public abstract class BlogRequest {
    private String query;
    private SearchBlogSort sort;
    private Integer page;
    private Integer size;
}
