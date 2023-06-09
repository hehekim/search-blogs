package com.dev.moduleclient.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlogSearchSort {
    ACCURACY("accuracy", "sim", "정확도순"),
    RECENCY("recency", "date", "최신순");

    private final String kakaoSort;
    private final String naverSort;
    private final String name;
}
