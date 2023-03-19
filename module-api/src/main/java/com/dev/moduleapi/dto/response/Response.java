package com.dev.moduleapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class Response<T> {
    private String resultCode;
    private T result;

    public static <T> Response<T> success() {
        return new Response<T>(HttpStatus.OK.name(), null);
    }

    public static <T> Response<T> success(T result) {
        return new Response<T>(HttpStatus.OK.name(), result);
    }

    public static Response<Void> fail(String resultCode) {
        return new Response<Void>(resultCode, null);
    }
}
