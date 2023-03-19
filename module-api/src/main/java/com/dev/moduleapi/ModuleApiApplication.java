package com.dev.moduleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = { "com.dev.moduleapi", "com.dev.moduleclient" }
)
@EntityScan(basePackages = "com.dev.moduledomain.entity")
@EnableJpaRepositories(basePackages = "com.dev.moduledomain.repository")
public class ModuleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleApiApplication.class, args);
    }

}
