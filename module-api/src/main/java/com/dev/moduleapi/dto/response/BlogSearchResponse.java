package com.dev.moduleapi.dto.response;


import com.dev.moduleapi.dto.request.BlogSearchRequest;
import com.dev.moduleclient.dto.response.KakaoBlogResponse;
import com.dev.moduleclient.dto.response.NaverBlogResponse;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogSearchResponse {

    List<SearchBlog> blogs;
    private PageInfo page;

    @Getter
    @Setter
    @Builder
    public static class SearchBlog {
        private String title;
        private String content;
        private String postUrl;
        private String blogName;
        private LocalDate postDate;

        public static SearchBlog from(KakaoBlogResponse.KaKaoDocuments response) {
            return SearchBlog.builder()
                    .title(response.getTitle())
                    .content(response.getContents())
                    .postUrl(response.getUrl())
                    .blogName(response.getBlogname())
                    .postDate(LocalDate.parse(response.getDatetime().substring(0, 10)))
                    .build();
        }

        public static SearchBlog from(NaverBlogResponse.NaverItem response) {
            return SearchBlog.builder()
                    .title(response.getTitle())
                    .content(response.getDescription())
                    .postUrl(response.getLink())
                    .blogName(response.getBloggername())
                    .postDate(LocalDate.parse(response.getPostdate(), DateTimeFormatter.ofPattern("yyyyMMdd")))
                    .build();
        }
    }

    public static BlogSearchResponse of(KakaoBlogResponse response, BlogSearchRequest request) {
        return BlogSearchResponse.builder()
                .blogs(response.getDocuments()
                        .stream()
                        .map(SearchBlog::from)
                        .collect(Collectors.toList()))
                .page(PageInfo.builder()
                        .page(response.getMeta().getPageableCount())
                        .totalCount(response.getMeta().getTotalCount())
                        .size(request.getSize())
                        .sort(request.getSort().name())
                        .build())
                .build();
    }

    public static BlogSearchResponse of(NaverBlogResponse response, BlogSearchRequest request) {
        return BlogSearchResponse.builder()
                .blogs(response.getItems()
                        .stream()
                        .map(SearchBlog::from)
                        .collect(Collectors.toList()))
                .page(PageInfo.builder()
                        .page(response.getStart())
                        .totalCount(response.getTotal())
                        .size(request.getSize())
                        .sort(request.getSort().name())
                        .build())
                .build();
    }
}
