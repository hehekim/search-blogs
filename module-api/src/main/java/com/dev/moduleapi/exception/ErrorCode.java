package com.dev.moduleapi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "요청 데이터가 올바르지 않습니다."),
    REQUEST_BODY_MISSING_ERROR(HttpStatus.BAD_REQUEST, "요청 데이터가 누락되거나 올바르지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 시스템 에러");

    private final HttpStatus status;
    private final String message;
}
