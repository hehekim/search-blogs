package com.dev.moduleapi.controller;

import com.dev.moduleapi.exception.ErrorCode;
import com.dev.moduleapi.exception.SearchApplicationException;
import com.dev.moduleapi.fixture.BlogPopularKeywordResponseFixture;
import com.dev.moduleapi.service.BlogPopularKeywordApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("인기키워드 Controller Test")
@WebMvcTest(BlogPopularKeywordController.class)
class BlogPopularKeywordControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private BlogPopularKeywordApiService popularKeywordService;

    @DisplayName("[GET] 인기키워드(검색 횟수가 많은 기준) 상위 10개 조회 - 성공")
    @Test
    void getTenPopularKeywords() throws Exception {
        // Given
        given(popularKeywordService.getTenPopularKeywords())
                .willReturn(BlogPopularKeywordResponseFixture.createTop10Keywords());

        // When & Then
        mvc.perform(get("/popular-keywords")
                    .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
        then(popularKeywordService).should().getTenPopularKeywords();
    }

    @DisplayName("[GET] 인기키워드(검색 횟수가 많은 기준) 상위 10개 조회 시 조회 데이터가 없을 때 - 에러발생")
    @Test
    void findTenPopularKeywordsReturnNoData() throws Exception {
        // Given
        given(popularKeywordService.getTenPopularKeywords())
                .willThrow(new SearchApplicationException(ErrorCode.POPULAR_KEYWORD_NOT_FOUND));

        // When & Then
        mvc.perform(get("/popular-keywords")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isNotFound());
        then(popularKeywordService).should().getTenPopularKeywords();
    }
}