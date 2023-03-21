package com.dev.moduleapi.fixture;

import com.dev.moduleclient.dto.response.BlogSearchResponse;
import com.dev.moduleclient.dto.response.PageInfo;

import java.time.LocalDate;
import java.util.List;

public class BlogSearchResponseFixture {
    public static BlogSearchResponse createBlogSearchResponse() {
        return BlogSearchResponse.builder()
                .blogs(List.of(BlogSearchResponse.BlogSearch.builder()
                                .title("블로그 제목")
                                .content("블로그 내용")
                                .postUrl("https://www.kakaocorp.com/")
                                .blogName("블로그 이름")
                                .postDate(LocalDate.now())
                        .build()))
                .page(PageInfo.builder()
                        .totalPage(10)
                        .page(1)
                        .size(10)
                        .totalCount(100)
                        .build())
                .build();
    }
}
