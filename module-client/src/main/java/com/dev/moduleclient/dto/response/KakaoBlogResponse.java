package com.dev.moduleclient.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class KakaoBlogResponse {
    List<KaKaoDocuments> documents = new ArrayList<>();
    KaKaoMeta meta;

    @Getter
    @Setter
    public static class KaKaoDocuments {
        private String title;       // 블로그 글 제목
        private String contents;    // 블로그 글 요약
        private String url;         // 블로그 글 URL
        private String blogname;    // 블로그의 이름
        private String thumbnail;   // 검색 시스템에서 추출한 대표 미리보기 이미지 URL
        private String datetime;    // 블로그 글 작성시간, ISO 8601
    }

    @Getter
    @Setter
    public static class KaKaoMeta {
        private Boolean isEnd;          // 현재 페이지가 마지막 페이지인지 여부
        private Integer pageableCount;  // total_count 중 노출 가능 문서 수
        private Integer totalCount;     // 검색된 문서 수
    }
}
