package com.dev.moduleclient.dto;

import lombok.Data;

import java.util.List;

@Data
public class NaverBlogResponse implements BlogResponse {
    private String lastBuildDate;   // 검색 결과를 생성한 시간
    private Integer total;          // 총 검색 결과 개수
    private Integer start;          // 검색 시작 위치
    private Integer display;        // 한 번에 표시할 검색 결과 개수
    private List<NaverItem> items;   // 개별 검색 결과

    @Data
    public static class  NaverItem {
        private String title;       // 블로그 포스트의 제목
        private String link;        // 블로그 포스트의 URL
        private String description; // 블로그 포스트의 내용을 요약한 패시지 정보
        private String bloggername; // 블로그 포스트가 있는 블로그의 이름
        private String bloggerlink; // 블로그 포스트가 있는 블로그의 주소
        private String postdate;    // 블로그 포스트가 작성된 날짜
    }
}
