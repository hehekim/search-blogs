package com.dev.moduleapi.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPopularKeywordEvent {
    private String keyword;

    public static BlogPopularKeywordEvent from(String keyword) {
        return new BlogPopularKeywordEvent(keyword);
    }
}
