package com.dev.moduleclient.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class BlogRequest {
    private String query;
    private BlogSearchSort sort;
    private Integer page;
    private Integer size;
}
