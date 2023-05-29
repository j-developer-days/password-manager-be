package com.jdev.passwordManager.restController;

import com.jdev.passwordManager.service.GroupAccountService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.jdev.passwordManager.GroupAccountHelper.GROUP_ACCOUNT_NAME;
import static com.jdev.passwordManager.GroupAccountHelper.ID;
import static org.mockito.Mockito.*;

@WebMvcTest(GroupAccountRestController.class)
class GroupAccountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupAccountService groupAccountService;

    @AfterEach
    private void mockitoAfterVerifyNoMoreInteractions() {
        verifyNoMoreInteractions(groupAccountService);
    }

    @Test
    void test_createGroupAccount() throws Exception {
        doNothing().when(groupAccountService).createGroupAccount(GROUP_ACCOUNT_NAME);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/group-account/" + GROUP_ACCOUNT_NAME).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("")));

        verify(groupAccountService).createGroupAccount(GROUP_ACCOUNT_NAME);
    }

    @Test
    void test_getGroupAccountById() throws Exception {
        when(groupAccountService.getGroupAccountById(ID)).thenReturn(GROUP_ACCOUNT_NAME);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/group-account/" + ID).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo(GROUP_ACCOUNT_NAME)));

        verify(groupAccountService).getGroupAccountById(any());
    }

    @Test
    void test_getGroupAccountById_notFound() throws Exception {
        when(groupAccountService.getGroupAccountById(ID)).thenReturn(null);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/group-account/" + ID).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("")));

        verify(groupAccountService).getGroupAccountById(any());
    }

    @Test
    void test_getAllGroupAccount_emptyResult() throws Exception {
        when(groupAccountService.getGroupAccounts()).thenReturn(List.of());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/group-account/").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("[]")));

        verify(groupAccountService).getGroupAccounts();
    }

    @Test
    void test_getAllGroupAccount() throws Exception {
        when(groupAccountService.getGroupAccounts()).thenReturn(List.of(GROUP_ACCOUNT_NAME));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/group-account/").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("[\"" + GROUP_ACCOUNT_NAME + "\"]")));

        verify(groupAccountService).getGroupAccounts();
    }


}