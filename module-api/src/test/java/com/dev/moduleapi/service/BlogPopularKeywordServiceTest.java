package com.dev.moduleapi.service;

import com.dev.moduleapi.exception.ErrorCode;
import com.dev.moduleapi.exception.SearchApplicationException;
import com.dev.moduleapi.fixture.BlogPopularKeywordEntityFixture;
import com.dev.moduledomain.service.PopularKeywordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("인기키워드 Service Test")
@ExtendWith(MockitoExtension.class)
class BlogPopularKeywordServiceTest {

    @InjectMocks
    BlogPopularKeywordApiService blogPopularKeywordApiService;

    @Mock
    PopularKeywordService popularKeywordService;

    @Test
    @DisplayName("인기키워드 상위 10개 조회 요청 - 성공")
    void findTop10ReturnSuccess() {
        // Given
        given(popularKeywordService.getTenPopularKeywords())
                .willReturn((BlogPopularKeywordEntityFixture.createTop10Keywords()));
        // When & Then
        assertDoesNotThrow(() -> blogPopularKeywordApiService.getTenPopularKeywords());
        then(popularKeywordService).should().getTenPopularKeywords();
    }

    @Test
    @DisplayName("인기키워드 상위 10개 조회 시 값이 없을 때 - 에러발생")
    void findTop10ReturnNotFoundError() {
         // Given
        given(popularKeywordService.getTenPopularKeywords()).willReturn(null);

        // When & Then
        SearchApplicationException exception =
                assertThrows(SearchApplicationException.class, () -> blogPopularKeywordApiService.getTenPopularKeywords());
        assertEquals(ErrorCode.POPULAR_KEYWORD_NOT_FOUND, exception.getErrorCode());
        then(popularKeywordService).should().getTenPopularKeywords();
    }
}