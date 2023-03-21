package com.dev.moduledomain.service;

import com.dev.moduledomain.entity.BlogPopularKeyword;
import com.dev.moduledomain.repository.BlogPopularKeywordRepository;
import com.dev.moduledomain.util.JpaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogPopularKeywordService implements PopularKeywordService {
    private final BlogPopularKeywordRepository popularKeywordRepository;
    @Override
    public void addPopularKeywordToOneCount(String keyword) {
        BlogPopularKeyword popularKeyword =  popularKeywordRepository.findByKeywordWithLock(keyword)
                .orElseGet(() -> BlogPopularKeyword.from(keyword));
        popularKeyword.addSearchCount();
        JpaUtils.SaveIfIdIsNull(popularKeyword.getId(), popularKeywordRepository, popularKeyword);
    }


}
