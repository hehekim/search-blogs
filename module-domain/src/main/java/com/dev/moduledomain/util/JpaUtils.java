package com.dev.moduledomain.util;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Objects;

public class JpaUtils {
    public static void SaveIfIdIsNull(Long id, JpaRepository repository, Object entity) {
        if(Objects.isNull(id)) {
            repository.save(entity);
        }
    }
}
