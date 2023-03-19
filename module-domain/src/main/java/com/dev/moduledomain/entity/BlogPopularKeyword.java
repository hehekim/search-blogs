package com.dev.moduledomain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "blog_popluar_keyword",
        indexes = {
                @Index(name = "idx_keyword", columnList = "keyword"),
        }
)
public class BlogPopularKeyword extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String keyword;     // 검색 키워드
    @Column(nullable = false)
    private Long searchCount;   // 검색 횟수

    public BlogPopularKeyword(String keyword, Long searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    public static BlogPopularKeyword from(String keyword) {
        final long SEARCH_COUNT = 1L;
        return new BlogPopularKeyword(
                keyword,
                SEARCH_COUNT
        );
    }

    public void addSearchCount() {
        this.searchCount += 1;
    }
}
