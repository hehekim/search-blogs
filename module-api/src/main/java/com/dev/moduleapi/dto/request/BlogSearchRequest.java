package com.dev.moduleapi.dto.request;

import com.dev.moduleclient.dto.request.BlogRequest;
import com.dev.moduleclient.dto.request.BlogSearchSort;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@Setter
@Builder
public class BlogSearchRequest extends BlogRequest {
    @NotBlank(message = "필수값 query 값이 없습니다.")
    private String query;           // 검색을 원하는 질의어
    private BlogSearchSort sort;    // 결과 문서 정렬 방식
    private Integer page;           // 결과 페이지 번호
    private Integer size;           // 한 페이지에 보여질 문서 수

    public BlogSearchRequest(String query, BlogSearchSort sort, Integer page, Integer size) {
        this.query = removeKeywordSpaces(query);
        this.sort = Objects.isNull(sort) ? BlogSearchSort.ACCURACY : sort;
        this.page = Objects.isNull(page) ? 1 : page;
        this.size = Objects.isNull(size) ? 10 : size;
    }

    private String removeKeywordSpaces(String keyword) {
        return keyword.replaceAll("\\s+", " ").trim();
    }
}
