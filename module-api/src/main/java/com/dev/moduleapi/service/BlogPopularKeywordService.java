package com.dev.moduleapi.service;

import com.dev.moduledomain.entity.BlogPopularKeyword;
import com.dev.moduledomain.repository.BlogPopularKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BlogPopularKeywordService {

    public static final long SEARCH_COUNT = 1L;
    private final BlogPopularKeywordRepository popularKeywordRepository;

    public void saveBlogPopularKeyword(String keyword) {
        BlogPopularKeyword popularKeyword = popularKeywordRepository.findByKeyword(keyword);

        if (Objects.nonNull(popularKeyword)) {
            popularKeyword.addSearchCount();
        } else {
            popularKeywordRepository.save(BlogPopularKeyword.builder()
                    .keyword(keyword)
                    .searchCount(SEARCH_COUNT)
                    .build());
        }

    }
}
