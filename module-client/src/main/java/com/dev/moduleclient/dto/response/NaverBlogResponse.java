package com.dev.moduleclient.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class NaverBlogResponse extends BlogResponse {
    @JsonProperty("lastBuildDate")
    private String lastBuildDate;   // 검색 결과를 생성한 시간
    @JsonProperty("total")
    private Integer total;          // 총 검색 결과 개수
    @JsonProperty("start")
    private Integer start;          // 검색 시작 위치
    @JsonProperty("display")
    private Integer display;        // 한 번에 표시할 검색 결과 개수
    private List<NaverItem> items;   // 개별 검색 결과

    @Getter
    @Setter
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
    }
}
