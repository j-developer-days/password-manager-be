package com.jdev.passwordManager.service;

import com.jdev.passwordManager.dto.request.AccountRequestDto;
import com.jdev.passwordManager.dto.response.AccountResponseDto;
import com.jdev.passwordManager.dto.response.CommonResponse;
import com.jdev.passwordManager.AccountHelper;
import com.jdev.passwordManager.GroupAccountHelper;
import com.jdev.passwordManager.entity.AccountEntity;
import com.jdev.passwordManager.exception.PasswordManagerRuntimeException;
import com.jdev.passwordManager.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private GroupAccountService groupAccountService;

    @AfterEach
    private void mockitoAfterVerifyNoMoreInteractions() {
        verifyNoMoreInteractions(accountRepository, groupAccountService);
    }

    @Test
    void test_createAccount_passwordIsDifferent() {
        AccountRequestDto accountRequestDto = AccountHelper.getAccountRequestDto();
        accountRequestDto.setAccountPasswordConfirm(accountRequestDto.getAccountPassword() + AccountHelper.ID);

        PasswordManagerRuntimeException passwordManagerRuntimeException = Assertions.assertThrows(PasswordManagerRuntimeException.class, () -> accountService.createAccount(accountRequestDto));

        Assertions.assertEquals("password and confirm password is different!", passwordManagerRuntimeException.getErrorMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, passwordManagerRuntimeException.getHttpStatus());
        Assertions.assertEquals(CommonResponse.ErrorType.NOT_EQUAL_PASSWORD, passwordManagerRuntimeException.getErrorType());
    }

    @Test
    void test_createAccount_success() {
        AccountEntity accountEntityResult = AccountHelper.getAccountEntity();
        accountEntityResult.setId(AccountHelper.ID);
        when(groupAccountService.getGroupAccountByName(GroupAccountHelper.GROUP_ACCOUNT_NAME)).thenReturn(Optional.of(GroupAccountHelper.getGroupAccountEntity()));
        AccountEntity accountEntity = AccountHelper.getAccountEntity();
        when(accountRepository.saveAndFlush(accountEntity)).thenReturn(accountEntityResult);

        Integer accountId = accountService.createAccount(AccountHelper.getAccountRequestDto());

        Assertions.assertEquals(AccountHelper.ID, accountId);

        verify(groupAccountService).getGroupAccountByName(GroupAccountHelper.GROUP_ACCOUNT_NAME);
        verify(accountRepository).saveAndFlush(accountEntity);
    }

    @Test
    void test_getAccounts_emptyResult() {
        when(accountRepository.findAll()).thenReturn(List.of());
        List<AccountResponseDto> accountResponseDtos = accountService.getAccounts();

        Assertions.assertEquals(List.of(), accountResponseDtos);

        verify(accountRepository).findAll();
    }

    @Test
    void test_getAccounts() {
        AccountEntity accountEntity = AccountHelper.getAccountEntity();
        accountEntity.setCreationZonedDateTime(AccountHelper.getAccountResponseDto().getCreationZonedDateTime());
        when(accountRepository.findAll()).thenReturn(List.of(accountEntity));
        List<AccountResponseDto> accountResponseDtos = accountService.getAccounts();

        Assertions.assertEquals(List.of(AccountHelper.getAccountResponseDto()), accountResponseDtos);

        verify(accountRepository).findAll();
    }

}