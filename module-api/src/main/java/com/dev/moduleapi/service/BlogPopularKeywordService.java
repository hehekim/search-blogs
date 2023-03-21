package com.dev.moduleapi.service;

import com.dev.moduleapi.dto.response.BlogPopularKeywordResponse;
import com.dev.moduleapi.exception.ErrorCode;
import com.dev.moduleapi.exception.SearchApplicationException;
import com.dev.moduledomain.entity.BlogPopularKeyword;
import com.dev.moduledomain.repository.BlogPopularKeywordRepository;
import com.dev.moduledomain.util.JpaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogPopularKeywordService {
    private final BlogPopularKeywordRepository popularKeywordRepository;

    public void saveBlogPopularKeyword(String keyword) {
        BlogPopularKeyword popularKeyword = popularKeywordRepository.findByKeywordWithLock(keyword)
                .orElseGet(() -> BlogPopularKeyword.from(keyword));
        popularKeyword.addSearchCount();
        JpaUtils.SaveIfIdIsNull(popularKeyword.getId(), popularKeywordRepository, popularKeyword);
    }

    private boolean isExistsPopularKeyword(BlogPopularKeyword popularKeyword) {
        return Objects.nonNull(popularKeyword);
    }

    public List<BlogPopularKeywordResponse> getTenPopularKeywords() {
        List<BlogPopularKeyword> popularKeywords = popularKeywordRepository.findTop10ByOrderBySearchCountDesc();

        if (isExistsPopularKeywords(popularKeywords)) {
            throw new SearchApplicationException(ErrorCode.POPULAR_KEYWORD_NOT_FOUND);
        }

        return popularKeywords.stream()
                .map(BlogPopularKeywordResponse::from)
                .collect(Collectors.toList());
    }

    private boolean isExistsPopularKeywords(List<BlogPopularKeyword> popularKeywords) {
        return CollectionUtils.isEmpty(popularKeywords);
    }
}
