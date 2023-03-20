package com.dev.moduleapi.service;

import com.dev.moduleapi.dto.BlogPopularKeywordEntityFixture;
import com.dev.moduleapi.exception.SearchApplicationException;
import com.dev.moduleapi.exception.ErrorCode;
import com.dev.moduledomain.repository.BlogPopularKeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("인기키워드 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class BlogPopularKeywordServiceTest {

    @InjectMocks
    BlogPopularKeywordService popularKeywordService;

    @Mock
    BlogPopularKeywordRepository blogPopularKeywordRepository;

    @Test
    @DisplayName("인기키워드 상위 10개 조회 요청 성공")
    void findTop10ReturnSuccess() {
        // Given
        when(blogPopularKeywordRepository.findTop10ByOrderBySearchCountDesc())
                .thenReturn(BlogPopularKeywordEntityFixture.createTop10Keywords());

        // When & Then
        assertDoesNotThrow(() -> popularKeywordService.getTenPopularKeywords());
    }

    @Test
    @DisplayName("인기키워드 상위 10개 조회 시 값이 없을 때 에러발생")
    void findTop10ReturnNotFoundError() {
        // Given
        when(blogPopularKeywordRepository.findTop10ByOrderBySearchCountDesc()).thenReturn(null);

        // When & Then
        SearchApplicationException exception = assertThrows(SearchApplicationException.class, () -> popularKeywordService.getTenPopularKeywords());
        assertEquals(ErrorCode.POPULAR_KEYWORD_NOT_FOUND, exception.getErrorCode());
    }
}