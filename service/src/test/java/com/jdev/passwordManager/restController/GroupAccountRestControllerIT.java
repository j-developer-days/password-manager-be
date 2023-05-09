package com.jdev.passwordManager.restController;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.jdev.passwordManager.GroupAccountHelper.GROUP_ACCOUNT_NAME;

@SpringBootTest
@AutoConfigureMockMvc
class GroupAccountRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_createGroupAccount() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/group-account/" + GROUP_ACCOUNT_NAME + "99").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("")));
    }

}