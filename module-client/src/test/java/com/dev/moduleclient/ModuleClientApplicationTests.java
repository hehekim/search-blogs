package com.dev.moduleclient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.config.location=classpath:application-client.yml"})
class ModuleClientApplicationTests {

    @Test
    void contextLoads() {
    }

}
