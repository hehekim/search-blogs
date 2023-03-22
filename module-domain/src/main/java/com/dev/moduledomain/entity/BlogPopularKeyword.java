package com.dev.moduledomain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "blog_popular_keyword",
        indexes = {
                @Index(name = "idx_keyword", columnList = "keyword"),
                @Index(name = "idx_search_count", columnList = "searchCount")
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

    public BlogPopularKeyword(String keyword) {
        this.keyword = keyword;
        this.searchCount = 0L;
    }

    public BlogPopularKeyword(String keyword, Long searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    public static BlogPopularKeyword from(String keyword) {
        return new BlogPopularKeyword(keyword);
    }

    public void addSearchCount() {
        this.searchCount += 1;
    }
}
