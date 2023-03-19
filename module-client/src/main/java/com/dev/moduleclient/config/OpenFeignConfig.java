package com.dev.moduleclient.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.dev.moduleclient")
public class OpenFeignConfig {
}
