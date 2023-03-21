package com.dev.moduleapi.controller;

import com.dev.moduleapi.dto.BlogPopularKeywordResponseFixture;
import com.dev.moduleapi.exception.SearchApplicationException;
import com.dev.moduleapi.exception.ErrorCode;
import com.dev.moduleapi.service.BlogPopularKeywordApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("인기키워드 컨트롤러 테스트")
@WebMvcTest(BlogPopularKeywordController.class)
class BlogPopularKeywordControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private BlogPopularKeywordApiService popularKeywordService;

    @DisplayName("[GET] 인기키워드(검색 횟수가 많은 기준) 상위 10개 조회")
    @Test
    void getTenPopularKeywords() throws Exception {
        // Given
        when(popularKeywordService.getTenPopularKeywords())
                .thenReturn(BlogPopularKeywordResponseFixture.createTop10Keywords());

        // When & Then
        mvc.perform(get("/popular-keywords")
                    .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("[GET] 인기키워드(검색 횟수가 많은 기준) 상위 10개 조회 시 조회 데이터가 없을 때 에러발생")
    @Test
    void findTenPopularKeywordsReturnNoData() throws Exception {
        // Given
        doThrow(new SearchApplicationException(ErrorCode.POPULAR_KEYWORD_NOT_FOUND))
                .when(popularKeywordService).getTenPopularKeywords();

        // When & Then
        mvc.perform(get("/popular-keywords")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isNotFound());
    }
}