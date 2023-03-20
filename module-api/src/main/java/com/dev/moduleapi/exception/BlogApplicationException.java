package com.dev.moduleapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlogApplicationException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public BlogApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        } else {
            return String.format("%s.", errorCode.getMessage());
        }
    }
}
