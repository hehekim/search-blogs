package com.dev.moduleapi.exception;

import com.dev.moduleapi.dto.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(SearchApplicationException.class)
    private ResponseEntity<?> handleBlogApplicationException(SearchApplicationException e) {
        log.error("[SearchApplicationException] {}, errorCode = {}", e.getMessage(), e.getErrorCode());
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.failed(e.getErrorCode().name()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<?> handleMethodArgumentValidException(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidException] Invalid data value 'object' or 'parameter' when API call.", e);
        return ResponseEntity.status(ErrorCode.INVALID_PARAMETER.getStatus())
                .body(Response.failed(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("[MethodArgumentTypeMismatchException] '{}' should be a valid '{}' and '{}' isn't", e.getName(), e.getRequiredType(), e.getValue());
        return ResponseEntity.status(ErrorCode.INVALID_PARAMETER.getStatus())
                .body(Response.failed(String.format("'%s' should be a valid '%s' and '%s' isn't",
                        e.getName(), e.getRequiredType(), e.getValue())));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("[HttpMessageNotReadableException] Exceptions due to missing request data, etc.", e);
        return ResponseEntity.status(ErrorCode.REQUEST_BODY_MISSING_ERROR.getStatus())
                .body(Response.failed(ErrorCode.REQUEST_BODY_MISSING_ERROR.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> handleException(Exception e) {
        log.error("[Exception] Exception has occurred. status={}, cause={}", NestedExceptionUtils.getMostSpecificCause(e).getCause(), e.getMessage());
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(Response.failed(ErrorCode.INTERNAL_SERVER_ERROR.getMessage()));
    }
}
