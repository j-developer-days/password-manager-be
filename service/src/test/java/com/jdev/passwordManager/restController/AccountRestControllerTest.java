package com.jdev.passwordManager.restController;

import com.jdev.passwordManager.dto.request.AccountRequestDto;
import com.jdev.passwordManager.dto.response.AccountResponseDto;
import com.jdev.passwordManager.dto.response.CommonResponse;
import com.jdev.passwordManager.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MimeTypeUtils;

import java.util.List;

import static com.jdev.passwordManager.AccountHelper.*;
import static org.mockito.Mockito.*;

@WebMvcTest(AccountRestController.class)
class AccountRestControllerTest extends RestControllerTestHelper {

    private static final String URL = "/account/";

    @MockBean
    private AccountService accountService;

    void mockitoAfter() {
        verifyNoMoreInteractions(accountService);
    }

    @Test
    void test_createAccount() throws Exception {
        AccountRequestDto accountRequestDto = getAccountRequestDto();
        when(accountService.createAccount(accountRequestDto)).thenReturn(ID);

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .header(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(accountRequestDto)).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(CommonResponse.success(ID))));

        verify(accountService).createAccount(accountRequestDto);
    }

    @Test
    void test_getAccounts() throws Exception {
        List<AccountResponseDto> accountResponseDtoList = List.of(getAccountResponseDto());
        when(accountService.getAccounts()).thenReturn(accountResponseDtoList);

        this.mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(CommonResponse.success(accountResponseDtoList))));

        verify(accountService).getAccounts();
    }

}