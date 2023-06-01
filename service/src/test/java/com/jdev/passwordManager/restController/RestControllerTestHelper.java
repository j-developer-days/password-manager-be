package com.jdev.passwordManager.restController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public abstract class RestControllerTestHelper {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @AfterEach
    private void mockitoAfterVerifyNoMoreInteractions() {
        mockitoAfter();
    }

    abstract void mockitoAfter();

}
