package com.dev.moduleapi.fixture;

import com.dev.moduleclient.dto.response.KakaoBlogResponse;

import java.util.List;

public class BlogResponseFixture {
    public static KakaoBlogResponse CreateKakaoBlogResponse() {
        return KakaoBlogResponse.builder()
                .documents(List.of(
                        KakaoBlogResponse.KaKaoDocument.builder()
                                .title("블로그 글 제목")
                                .contents("블로그 글 요약")
                                .url("블로그 글 URL")
                                .blogname("블로그의 이름")
                                .thumbnail("검색 시스템에서 추출한 대표 미리보기 이미지 URL")
                                .datetime("블로그 글 작성시간")
                                .build()
                ))
                .meta(
                        KakaoBlogResponse.KaKaoMeta.builder()
                                .isEnd(false)
                                .pageableCount(500)
                                .totalCount(700)
                                .build()
                )
                .build();
    }
}
