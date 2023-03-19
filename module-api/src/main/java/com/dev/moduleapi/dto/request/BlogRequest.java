package com.dev.moduleapi.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BlogRequest {
    private String query;   // 검색을 원하는 질의어
    // TODO  enum 고려
    private String sort;    // 결과 문서 정렬 방식
    private Integer page;   // 결과 페이지 번호
    private Integer size;   // 한 페이지에 보여질 문서 수

    public static BlogRequest of(String query, String sort, Integer page, Integer size) {
        return BlogRequest.builder()
                .query(removeKeywordSpaces(query))
                .sort(sort)
                .page(page)
                .size(size)
                .build();
    }

    private static String removeKeywordSpaces(String keyword) {
        return keyword.replaceAll("\\s+", " ").trim();
    }
}
