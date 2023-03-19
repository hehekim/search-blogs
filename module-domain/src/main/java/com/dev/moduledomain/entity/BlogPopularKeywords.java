package com.dev.moduledomain.entity;

import lombok.AccessLevel;
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
public class BlogPopularKeywords extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String keyword;     // 검색 키워드
    @Column(nullable = false)
    private Long searchCount;   // 검색 횟수
}
