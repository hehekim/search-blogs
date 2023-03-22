package com.dev.moduleclient.dto.response;

import com.dev.moduleclient.dto.request.BlogRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NaverBlogResponse extends BlogResponse {
    private static final Integer NAVER_MAX_PAGE = 1000;
    private static final Integer NAVER_MAX_SIZE = 100;
    @JsonProperty("lastBuildDate")
    private String lastBuildDate;   // 검색 결과를 생성한 시간
    @JsonProperty("total")
    private Integer total;          // 총 검색 결과 개수
    @JsonProperty("start")
    private Integer start;          // 검색 시작 위치
    @JsonProperty("display")
    private Integer display;        // 한 번에 표시할 검색 결과 개수
    private List<NaverItem> items;   // 개별 검색 결과

    public static BlogSearchResponse toBlogSearchResponse(BlogRequest request, BlogResponse data) {
        NaverBlogResponse response = (NaverBlogResponse) data;
        int size = PageInfo.getCurrentSize(NAVER_MAX_SIZE, request.getSize());
        int totalCount = PageInfo.getTotalSize(NAVER_MAX_PAGE, NAVER_MAX_SIZE, response.getTotal());
        int totalPage = PageInfo.getTotalPage(NAVER_MAX_PAGE, totalCount, size);
        int page = PageInfo.getCurrentPage(request.getPage(), totalPage);

        return BlogSearchResponse.builder()
                .blogs(response.getItems()
                        .stream()
                        .map(NaverBlogResponse.NaverItem::toBlogSearch)
                        .collect(Collectors.toList()))
                .page(PageInfo.builder()
                        .page(page)
                        .size(size)
                        .totalPage(totalPage)
                        .totalCount(totalCount)
                        .sort(request.getSort().name())
                        .build())
                .build();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NaverItem {
        @JsonProperty("title")
        private String title;       // 블로그 포스트의 제목
        @JsonProperty("link")
        private String link;        // 블로그 포스트의 URL
        @JsonProperty("description")
        private String description; // 블로그 포스트의 내용을 요약한 패시지 정보
        @JsonProperty("bloggername")
        private String bloggername; // 블로그 포스트가 있는 블로그의 이름
        @JsonProperty("bloggerlink")
        private String bloggerlink; // 블로그 포스트가 있는 블로그의 주소
        @JsonProperty("postdate")
        private String postdate;    // 블로그 포스트가 작성된 날짜

        public static BlogSearchResponse.BlogSearch toBlogSearch(NaverBlogResponse.NaverItem response) {
            return BlogSearchResponse.BlogSearch.builder()
                    .title(response.getTitle())
                    .contents(response.getDescription())
                    .postUrl(response.getLink())
                    .blogName(response.getBloggername())
                    .postDate(LocalDate.parse(response.getPostdate(), DateTimeFormatter.ofPattern("yyyyMMdd")))
                    .build();
        }
    }
}
