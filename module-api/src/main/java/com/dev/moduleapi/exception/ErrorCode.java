package com.dev.moduleapi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "올바르지 않는 요청 데이터 에러"),
    REQUEST_BODY_MISSING_ERROR(HttpStatus.BAD_REQUEST, "누락되거나 올바르지 않는 데이터 요청 에러"),
    POPULAR_KEYWORD_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 인기키워드 요청 에러"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 시스템 에러");

    private final HttpStatus status;
    private final String message;
}
