package com.dev.moduleapi.dto;

import com.dev.moduledomain.entity.BlogPopularKeyword;

import java.util.List;

public class BlogPopularKeywordEntityFixture {
    public static List<BlogPopularKeyword> createTop10Keywords() {
        return List.of(
                new BlogPopularKeyword("사과",10L),
                new BlogPopularKeyword("바나나",9L),
                new BlogPopularKeyword("포도",8L),
                new BlogPopularKeyword("레몬",7L),
                new BlogPopularKeyword("감",6L),
                new BlogPopularKeyword("도토리",5L),
                new BlogPopularKeyword("당근",4L),
                new BlogPopularKeyword("토마토",3L),
                new BlogPopularKeyword("수박",2L),
                new BlogPopularKeyword("오렌지",1L)
        );
    }
}
