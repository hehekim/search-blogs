package com.dev.moduleclient.dto.response;


import com.dev.moduleclient.client.SearchClientType;
import com.dev.moduleclient.dto.request.BlogRequest;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogSearchResponse {
    List<BlogSearch> blogs;
    private PageInfo page;

    @Getter
    @Setter
    @Builder
    public static class BlogSearch {
        private String title;
        private String content;
        private String postUrl;
        private String blogName;
        private LocalDate postDate;
    }

    public static BlogSearchResponse of(SearchClientType type, BlogRequest request, BlogResponse response) {
        switch (type) {
            case KAKAO_BLOG_SEARCH -> {
                return KakaoBlogResponse.toBlogSearchResponse(request, response);
            }
            case NAVER_BLOG_SEARCH -> {
                return NaverBlogResponse.toBlogSearchResponse(request, response);
            }
        }
        return null;
    }
}
