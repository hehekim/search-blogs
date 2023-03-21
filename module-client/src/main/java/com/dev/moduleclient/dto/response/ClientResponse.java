package com.dev.moduleclient.dto.response;

import com.dev.modulecommon.response.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse<T> implements CommonResponse {
    T result;

    public static <T> ClientResponse<T> success(T result) {
        return new ClientResponse<>(result);
    }

    public static <T> ClientResponse<T> failed(Exception exception) {
        return new ClientResponse<>();
    }
}
