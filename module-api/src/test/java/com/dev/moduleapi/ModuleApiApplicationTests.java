package com.dev.moduleapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.config.location=classpath:application-client.yml"})
class ModuleApiApplicationTests {

    @Test
    void contextLoads() {
    }

}
