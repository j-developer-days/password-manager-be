package com.jdev.passwordManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

public class ContextIT extends ParentTestHelper {

    @Test
    void context(ApplicationContext ctx) {
        Assertions.assertEquals(211, ctx.getBeanDefinitionCount());
    }

    @Override
    protected String getUrlWithRestControllerRequestMapping() {
        return null;
    }
}
