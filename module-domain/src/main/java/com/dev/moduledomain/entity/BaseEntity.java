package com.dev.moduledomain.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
    // TODO auditing 고려
    @Column(name = "create_at", nullable = false)
    protected LocalDateTime createAt;

    @Column(name = "update_at")
    protected LocalDateTime updateAt;

    @PrePersist
    public void createAt() {
        this.createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void updateAt() {
        this.updateAt = LocalDateTime.now();
    }
}
