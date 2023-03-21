package com.dev.moduledomain.service;

import com.dev.moduledomain.dto.resopnse.BlogPopularKeywordResponse;
import com.dev.moduledomain.dto.resopnse.DomainResponse;

import java.util.List;

public interface PopularKeywordService {
    void addPopularKeywordToOneCount(String keyword);
    DomainResponse<List<BlogPopularKeywordResponse>> getTenPopularKeywords();
}
