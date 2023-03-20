package com.dev.moduleapi.dto;

import com.dev.moduleapi.dto.response.BlogPopularKeywordResponse;

import java.util.List;

public class BlogPopularKeywordResponseFixture {
    public static List<BlogPopularKeywordResponse> createTop10Keywords() {
        return List.of(
                new BlogPopularKeywordResponse("사과",10L),
                new BlogPopularKeywordResponse("바나나",9L),
                new BlogPopularKeywordResponse("포도",8L),
                new BlogPopularKeywordResponse("레몬",7L),
                new BlogPopularKeywordResponse("감",6L),
                new BlogPopularKeywordResponse("도토리",5L),
                new BlogPopularKeywordResponse("당근",4L),
                new BlogPopularKeywordResponse("토마토",3L),
                new BlogPopularKeywordResponse("수박",2L),
                new BlogPopularKeywordResponse("오렌지",1L)
        );
    }
}
