package com.dev.moduledomain.service;

import com.dev.moduledomain.dto.resopnse.BlogPopularKeywordResponse;
import com.dev.moduledomain.dto.resopnse.DomainResponse;
import com.dev.moduledomain.entity.BlogPopularKeyword;
import com.dev.moduledomain.repository.BlogPopularKeywordRepository;
import com.dev.moduledomain.util.JpaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogPopularKeywordService implements PopularKeywordService {
    private final BlogPopularKeywordRepository popularKeywordRepository;

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

    @Override
    public void addPopularKeywordToOneCount(String keyword) {
        try {
            BlogPopularKeyword popularKeyword =  popularKeywordRepository.findByKeywordWithLock(keyword)
                    .orElseGet(() -> BlogPopularKeyword.from(keyword));
            popularKeyword.addSearchCount();
            JpaUtils.SaveIfIdIsNull(popularKeyword.getId(), popularKeywordRepository, popularKeyword);
        } catch (DataIntegrityViolationException e) {
            log.error("[DataIntegrityViolationException] Occurred by storing duplicate values in a database. request keyword = '{}'", keyword);
        }
    }

    private boolean isExistsPopularKeywords(List<BlogPopularKeyword> popularKeywords) {
        return CollectionUtils.isEmpty(popularKeywords);
    }

}
