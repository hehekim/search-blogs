package com.dev.moduleapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.dev.moduledomain.entity")
@EnableJpaRepositories(basePackages = "com.dev.moduledomain.repository")
@EnableJpaAuditing
@Configuration
public class JpaConfig {
}
