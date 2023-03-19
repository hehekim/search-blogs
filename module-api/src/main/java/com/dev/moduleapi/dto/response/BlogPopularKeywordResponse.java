package com.dev.moduleapi.dto.response;

import com.dev.moduledomain.entity.BlogPopularKeyword;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlogPopularKeywordResponse {
    private String keyword;
    private Long count;

    public static BlogPopularKeywordResponse from(BlogPopularKeyword entity) {
        return new BlogPopularKeywordResponse(
                entity.getKeyword(),
                entity.getSearchCount()
        );
    }
}
