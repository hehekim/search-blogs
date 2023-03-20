package com.dev.moduleclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModuleClientApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-client");
        SpringApplication.run(ModuleClientApplication.class, args);
    }

}
