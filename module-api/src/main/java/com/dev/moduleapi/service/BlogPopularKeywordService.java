package com.dev.moduleapi.service;

import com.dev.moduledomain.entity.BlogPopularKeyword;
import com.dev.moduledomain.repository.BlogPopularKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BlogPopularKeywordService {
    private final BlogPopularKeywordRepository popularKeywordRepository;

    public void saveBlogPopularKeyword(String keyword) {
        BlogPopularKeyword popularKeyword = popularKeywordRepository.findByKeyword(keyword);

        if (isExistsPopularKeyword(popularKeyword)) {
            popularKeyword.addSearchCount();
        } else {
            popularKeywordRepository.save(BlogPopularKeyword.from(keyword));
        }
    }

    private boolean isExistsPopularKeyword(BlogPopularKeyword popularKeyword) {
        return Objects.nonNull(popularKeyword);
    }
}
