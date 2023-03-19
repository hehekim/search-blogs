package com.dev.moduleapi.dto.response;


import com.dev.moduleapi.dto.request.BlogRequest;
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
public class SearchBlogResponse {

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
                    .postDate(LocalDate.parse(response.getDatetime(), DateTimeFormatter.ofPattern("yyyyMMdd")))
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

    public static SearchBlogResponse of(KakaoBlogResponse response, BlogRequest request) {
        return SearchBlogResponse.builder()
                .blogs(response.getDocuments()
                        .stream()
                        .map(SearchBlog::from)
                        .collect(Collectors.toList()))
                .page(PageInfo.builder()
                        .page(response.getMeta().getPageableCount())
                        .totalCount(response.getMeta().getTotalCount())
                        .size(request.getSize())
                        .sort(request.getSort())
                        .build())
                .build();
    }

    public static SearchBlogResponse of(NaverBlogResponse response, BlogRequest request) {
        return SearchBlogResponse.builder()
                .blogs(response.getItems()
                        .stream()
                        .map(SearchBlog::from)
                        .collect(Collectors.toList()))
                .page(PageInfo.builder()
                        .page(response.getStart())
                        .totalCount(response.getTotal())
                        .size(request.getSize())
                        .sort(request.getSort())
                        .build())
                .build();
    }
}
