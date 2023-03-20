package com.dev.moduleclient.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageInfo {
    private int totalCount;     // 전체 결과 갯수
    private int size;           // 페이지 당 보여줄 결과 갯수
    private int page;           // 현재 페이지
    private String sort;        // 정렬 방법
}
