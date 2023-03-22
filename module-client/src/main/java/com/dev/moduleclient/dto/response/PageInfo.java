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

    public static int getTotalPage(Integer maxPage, Integer totalSize, Integer size) {
        int totalPage = (int) Math.ceil((double) totalSize / size);
        return totalPage < maxPage ? totalPage : maxPage;
    }

    public static int getTotalSize(Integer maxPage, Integer maxSize, Integer totalSize) {
        int totalCount = maxPage * maxSize;
        return totalSize > totalCount ? totalCount : totalSize;
    }

    public static int getCurrentPage(Integer page, Integer totalPage) {
        return page > totalPage ? totalPage : page;
    }

    public static int getCurrentSize(Integer fixSize, Integer size) {
        return size > fixSize ? fixSize : size;
    }
}
