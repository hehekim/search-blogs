package com.dev.moduleapi.service;

import com.dev.moduleapi.exception.BlogApplicationException;
import com.dev.moduleapi.exception.ErrorCode;
import com.dev.moduledomain.entity.BlogPopularKeyword;
import com.dev.moduledomain.repository.BlogPopularKeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
        // given
        when(blogPopularKeywordRepository.findTop10ByOrderBySearchCountDesc())
                .thenReturn(createTop10Keywords());

        // when & then
        assertDoesNotThrow(() -> popularKeywordService.getTenPopularKeywords());
    }

    @Test
    @DisplayName("인기키워드 상위 10개 조회 시 값이 없을 때 에러발생")
    void findTop10ReturnNotFoundError() {
        // given
        when(blogPopularKeywordRepository.findTop10ByOrderBySearchCountDesc()).thenReturn(null);

        // when & then
        BlogApplicationException exception = assertThrows(BlogApplicationException.class, () -> popularKeywordService.getTenPopularKeywords());
        assertEquals(ErrorCode.POPULAR_KEYWORD_NOT_FOUND, exception.getErrorCode());
    }

    private List<BlogPopularKeyword> createTop10Keywords() {
        return List.of(
                new BlogPopularKeyword("사과",10L),
                new BlogPopularKeyword("바나나",9L),
                new BlogPopularKeyword("포도",8L),
                new BlogPopularKeyword("레몬",7L),
                new BlogPopularKeyword("감",6L),
                new BlogPopularKeyword("도토리",5L),
                new BlogPopularKeyword("당근",4L),
                new BlogPopularKeyword("토마토",3L),
                new BlogPopularKeyword("수박",2L),
                new BlogPopularKeyword("오렌지",1L)
        );
    }
}