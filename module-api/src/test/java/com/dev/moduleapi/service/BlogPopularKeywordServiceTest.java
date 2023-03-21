package com.dev.moduleapi.service;

import com.dev.moduleapi.dto.BlogPopularKeywordEntityFixture;
import com.dev.moduleapi.exception.ExceptionHandler;
import com.dev.moduleapi.exception.SearchApplicationException;
import com.dev.moduleapi.exception.ErrorCode;
import com.dev.moduledomain.dto.resopnse.BlogPopularKeywordResponse;
import com.dev.moduledomain.dto.resopnse.DomainResponse;
import com.dev.moduledomain.service.PopularKeywordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@DisplayName("인기키워드 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class BlogPopularKeywordServiceTest {

    @InjectMocks
    BlogPopularKeywordApiService blogPopularKeywordApiService;

    @Mock
    PopularKeywordService popularKeywordService;

    @Test
    @DisplayName("인기키워드 상위 10개 조회 요청 성공")
    void findTop10ReturnSuccess() {
        // Given
        when(popularKeywordService.getTenPopularKeywords())
                .thenReturn((BlogPopularKeywordEntityFixture.createTop10Keywords()));
        // When & Then
        assertDoesNotThrow(() -> blogPopularKeywordApiService.getTenPopularKeywords());
    }

    @Test
    @DisplayName("인기키워드 상위 10개 조회 시 값이 없을 때 에러발생")
    void findTop10ReturnNotFoundError() {
         // Given
        when(popularKeywordService.getTenPopularKeywords()).thenReturn(null);

        // When & Then
        SearchApplicationException exception =
                assertThrows(SearchApplicationException.class, () -> blogPopularKeywordApiService.getTenPopularKeywords());
        assertEquals(ErrorCode.POPULAR_KEYWORD_NOT_FOUND, exception.getErrorCode());
    }
}