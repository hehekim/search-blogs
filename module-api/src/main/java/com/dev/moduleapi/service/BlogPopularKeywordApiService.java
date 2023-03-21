package com.dev.moduleapi.service;

import com.dev.moduleapi.exception.ErrorCode;
import com.dev.moduleapi.exception.ExceptionHandler;
import com.dev.moduledomain.dto.resopnse.BlogPopularKeywordResponse;
import com.dev.moduledomain.dto.resopnse.DomainResponse;
import com.dev.moduledomain.service.PopularKeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogPopularKeywordApiService {
    private final PopularKeywordService popularKeywordService;

    public void saveBlogPopularKeyword(String keyword) {
        try {
            popularKeywordService.addPopularKeywordToOneCount(keyword);
        } catch (DataIntegrityViolationException e) {
            log.error("Occurred by storing duplicate values in a database. request keyword = '{}'", keyword);
        }
    }

    public List<BlogPopularKeywordResponse> getTenPopularKeywords() {
        DomainResponse<List<BlogPopularKeywordResponse>> response = popularKeywordService.getTenPopularKeywords();
        ExceptionHandler.checkException(response, ErrorCode.POPULAR_KEYWORD_NOT_FOUND);
        return response.getResult();
    }
}
