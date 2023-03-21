package com.dev.moduledomain.util;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Objects;

public class JpaUtils {
    public static <T, ID> void SaveIfIdIsNull(Long id, JpaRepository<T, ID> repository, T entity) {
        if(Objects.isNull(id)) {
            repository.save(entity);
        }
    }
}
