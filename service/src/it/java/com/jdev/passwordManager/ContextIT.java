package com.jdev.passwordManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(/*classes = Application.class,*/ webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContextIT {

    @Test
    void context(ApplicationContext ctx) {
        Assertions.assertEquals(204, ctx.getBeanDefinitionCount());
    }

}