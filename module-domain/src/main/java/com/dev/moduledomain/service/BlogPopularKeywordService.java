package com.dev.moduledomain.service;

import com.dev.moduledomain.dto.resopnse.BlogPopularKeywordResponse;
import com.dev.moduledomain.dto.resopnse.DomainResponse;
import com.dev.moduledomain.entity.BlogPopularKeyword;
import com.dev.moduledomain.repository.BlogPopularKeywordRepository;
import com.dev.moduledomain.util.JpaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public DomainResponse<List<BlogPopularKeywordResponse>> getTenPopularKeywords() {
        List<BlogPopularKeyword> popularKeywords = popularKeywordRepository.findTop10ByOrderBySearchCountDesc();
        if (isExistsPopularKeywords(popularKeywords)) {
            return DomainResponse.failed(new EntityNotFoundException());
        }

        return DomainResponse.success(popularKeywords.stream()
                .map(BlogPopularKeywordResponse::from)
                .collect(Collectors.toList()));
    }

    private boolean isExistsPopularKeywords(List<BlogPopularKeyword> popularKeywords) {
        return CollectionUtils.isEmpty(popularKeywords);
    }

}
