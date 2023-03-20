package com.dev.moduleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = { "com.dev.moduleapi", "com.dev.moduleclient" }
)
public class ModuleApiApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application, application-client, application-domain");
        SpringApplication.run(ModuleApiApplication.class, args);
    }
}
