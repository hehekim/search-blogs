package com.dev.moduleapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class Response<T> {
    private String resultCode;
    private T results;

    public static <T> Response<T> success(T result) {
        return new Response<>(HttpStatus.OK.name(), result);
    }

    public static Response<Void> failed(String resultCode) {
        return new Response<>(resultCode, null);
    }
}
