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
    private int totalPage;      // 전체 페이지
    private String sort;        // 정렬 방법

    public static int getTotalPage(Integer fixSize, Integer totalSize, Integer size) {
        int totalPage = ((totalSize - 1) / size) + 1;
        return totalPage > fixSize ? fixSize : totalPage;
    }

    public static int getTotalSize(Integer maxPage, Integer maxSize, Integer totalSize) {
        int totalCount = maxPage * maxSize;
        return totalSize > totalCount ? totalCount : totalSize;
    }

    public static int getCurrentPage(Integer fixSize, Integer page) {
        return page > fixSize ? fixSize : page;
    }

    public static int getCurrentSize(Integer fixSize, Integer size) {
        return size > fixSize ? fixSize : size;
    }
}
