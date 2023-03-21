package com.dev.moduledomain.dto.resopnse;

import com.dev.modulecommon.response.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DomainResponse<T> implements CommonResponse {
    T result;

    public static <T> DomainResponse<T> success(T result) {
        return new DomainResponse<>(result);
    }

    public static <T> DomainResponse<T> failed(Exception exception) {
        return new DomainResponse<>();
    }


}
