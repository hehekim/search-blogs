package com.dev.moduleclient.dto.response;

import com.dev.moduleclient.dto.request.BlogRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class KakaoBlogResponse extends BlogResponse {
    List<KaKaoDocument> documents = new ArrayList<>();
    KaKaoMeta meta;

    public static BlogSearchResponse toBlogSearchResponse(BlogRequest request, BlogResponse data) {
        KakaoBlogResponse response = (KakaoBlogResponse) data;
        return BlogSearchResponse.builder()
                .blogs(response.getDocuments()
                        .stream()
                        .map(KaKaoDocument::toKakaoDocument)
                        .collect(Collectors.toList()))
                .page(PageInfo.builder()
                        .page(request.getPage())
                        .totalPage(PageInfo.getTotalPage(response.getMeta().getPageableCount(), request.getSize()))
                        .totalCount(response.getMeta().getPageableCount())
                        .size(request.getSize())
                        .sort(request.getSort().name())
                        .build())
                .build();
    }

    @Getter
    @Setter
    @Builder
    public static class KaKaoDocument {
        @JsonProperty("title")
        private String title;       // 블로그 글 제목
        @JsonProperty("contents")
        private String contents;    // 블로그 글 요약
        @JsonProperty("url")
        private String url;         // 블로그 글 URL
        @JsonProperty("blogname")
        private String blogname;    // 블로그의 이름
        @JsonProperty("thumbnail")
        private String thumbnail;   // 검색 시스템에서 추출한 대표 미리보기 이미지 URL
        @JsonProperty("datetime")
        private String datetime;    // 블로그 글 작성시간, ISO 8601

        public static BlogSearchResponse.BlogSearch toKakaoDocument(KaKaoDocument response) {
            return BlogSearchResponse.BlogSearch.builder()
                    .title(response.getTitle())
                    .content(response.getContents())
                    .postUrl(response.getUrl())
                    .blogName(response.getBlogname())
                    .postDate(LocalDate.parse(response.getDatetime().substring(0, 10)))
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class KaKaoMeta {
        @JsonProperty("is_end")
        private Boolean isEnd;          // 현재 페이지가 마지막 페이지인지 여부
        @JsonProperty("pageable_count")
        private Integer pageableCount;  // total_count 중 노출 가능 문서 수
        @JsonProperty("total_count")
        private Integer totalCount;     // 검색된 문서 수
    }
}
