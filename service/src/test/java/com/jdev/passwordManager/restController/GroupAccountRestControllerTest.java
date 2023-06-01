package com.jdev.passwordManager.restController;

import com.jdev.passwordManager.dto.response.CommonResponse;
import com.jdev.passwordManager.service.GroupAccountService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.jdev.passwordManager.GroupAccountHelper.GROUP_ACCOUNT_NAME;
import static com.jdev.passwordManager.GroupAccountHelper.ID;
import static org.mockito.Mockito.*;

@WebMvcTest(GroupAccountRestController.class)
class GroupAccountRestControllerTest extends RestControllerTestHelper {

    private static final String URL = "/group-account/";

    @MockBean
    private GroupAccountService groupAccountService;

    @Override
    void mockitoAfter() {
        verifyNoMoreInteractions(groupAccountService);
    }

    @Test
    void test_createGroupAccount() throws Exception {
        when(groupAccountService.createGroupAccount(GROUP_ACCOUNT_NAME)).thenReturn(ID);

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL + GROUP_ACCOUNT_NAME).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(CommonResponse.success(ID))));

        verify(groupAccountService).createGroupAccount(GROUP_ACCOUNT_NAME);
    }

    @Test
    void test_getGroupAccountById() throws Exception {
        when(groupAccountService.getGroupAccountById(ID)).thenReturn(GROUP_ACCOUNT_NAME);

        this.mockMvc.perform(MockMvcRequestBuilders.get(URL + ID).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(CommonResponse.success(GROUP_ACCOUNT_NAME))));

        verify(groupAccountService).getGroupAccountById(any());
    }

    @Test
    void test_getGroupAccounts_emptyResult() throws Exception {
        when(groupAccountService.getGroupAccounts()).thenReturn(List.of());

        this.mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(CommonResponse.success(List.of()))));

        verify(groupAccountService).getGroupAccounts();
    }

    @Test
    void test_getGroupAccounts() throws Exception {
        when(groupAccountService.getGroupAccounts()).thenReturn(List.of(GROUP_ACCOUNT_NAME));

        this.mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(CommonResponse.success(List.of(GROUP_ACCOUNT_NAME)))));

        verify(groupAccountService).getGroupAccounts();
    }


}