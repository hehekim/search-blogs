package com.dev.moduleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = { "com.dev.moduleapi", "com.dev.moduleclient" }
)
//@EntityScan("com.dev.moduledomain.domain")
//@EnableJpaRepositories(basePackages = "dev.be.modulecommon.repositories")
public class ModuleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleApiApplication.class, args);
    }

}
