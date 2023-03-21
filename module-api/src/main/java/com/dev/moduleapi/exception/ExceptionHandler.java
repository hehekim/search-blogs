package com.dev.moduleapi.exception;

import com.dev.modulecommon.response.CommonResponse;

import java.util.Objects;

public class ExceptionHandler {

    public static void checkException(CommonResponse response, ErrorCode errorCode) {
        if (Objects.isNull(response.getResult())) {
            throw new SearchApplicationException(errorCode);
        }
    }
}
