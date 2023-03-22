package com.dev.moduleapi.service;

import com.dev.moduleapi.exception.SearchApplicationException;
import com.dev.moduleapi.fixture.BlogPopularKeywordEntityFixture;
import com.dev.moduledomain.service.PopularKeywordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

        // When
        blogPopularKeywordApiService.getTenPopularKeywords();

        // Then
        then(popularKeywordService).should().getTenPopularKeywords();
        assertDoesNotThrow(() -> blogPopularKeywordApiService.getTenPopularKeywords());
    }

    @Test
    @DisplayName("인기키워드 상위 10개 조회 시 값이 없을 때 - 에러발생")
    void findTop10ReturnNotFoundError() {
         // Given
        given(popularKeywordService.getTenPopularKeywords()).willReturn(null);

        // When
        Throwable t = catchThrowable(() -> blogPopularKeywordApiService.getTenPopularKeywords());

        // Then
        assertThat(t)
                .isInstanceOf(SearchApplicationException.class)
                .hasMessage("존재하지 않는 인기키워드 요청 에러");
        then(popularKeywordService).should().getTenPopularKeywords();
    }
}