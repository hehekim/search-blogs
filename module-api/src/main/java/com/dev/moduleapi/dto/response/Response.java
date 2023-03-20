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
        return new Response<>(HttpStatus.OK.name(), null);
    }

    public static <T> Response<T> success(T result) {
        return new Response<>(HttpStatus.OK.name(), result);
    }

    public static Response<Void> error(String resultCode) {
        return new Response<>(resultCode, null);
    }
}
