package com.dev.moduleapi.controller;

import com.dev.moduleapi.dto.request.BlogSearchRequest;
import com.dev.moduleapi.exception.ErrorCode;
import com.dev.moduleapi.exception.SearchApplicationException;
import com.dev.moduleapi.fixture.BlogSearchResponseFixture;
import com.dev.moduleapi.service.BlogSearchApiService;
import com.dev.moduleclient.dto.request.BlogSearchSort;
import com.dev.moduledomain.service.PopularKeywordEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("블로그검색 컨트롤러 테스트")
@WebMvcTest(BlogSearchController.class)
class BlogSearchControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private BlogSearchApiService blogService;
    @MockBean
    private PopularKeywordEventService popularKeywordEventService;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("[POST] 키워드 기준으로 블로그 검색 - 성공")
    @Test
    void searchBlogByKeyword() throws Exception {
        // Given
        given(blogService.searchBlogsByKeyword(any(BlogSearchRequest.class)))
                .willReturn(BlogSearchResponseFixture.createBlogSearchResponse());

        // When & Then
        mvc.perform(post("/blogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                BlogSearchRequest.builder()
                                        .query("사과")
                                        .sort(BlogSearchSort.ACCURACY)
                                        .page(1)
                                        .size(10)
                                        .build()
                        ))
                ).andDo(print())
                .andExpect(status().isOk());
        then(blogService).should().searchBlogsByKeyword(any(BlogSearchRequest.class));
    }

    @DisplayName("[POST] 키워드 기준으로 블로그 검색 - 에러발생")
    @Test
    void searchBlogByKeywordReturnError() throws Exception {
        // Given
        given(blogService.searchBlogsByKeyword(any(BlogSearchRequest.class)))
                .willThrow(new SearchApplicationException(ErrorCode.EXTERNAL_REQUEST_FAILED));

        // When & Then
        mvc.perform(post("/blogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                BlogSearchRequest.builder()
                                        .query("사과")
                                        .sort(BlogSearchSort.ACCURACY)
                                        .page(1)
                                        .size(10)
                                        .build()
                        ))
                ).andDo(print())
                .andExpect(status().isInternalServerError());
        then(blogService).should().searchBlogsByKeyword(any(BlogSearchRequest.class));
    }

}