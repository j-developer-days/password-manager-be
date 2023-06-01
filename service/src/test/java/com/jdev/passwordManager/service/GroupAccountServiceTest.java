package com.jdev.passwordManager.service;

import com.jdev.passwordManager.dto.response.CommonResponse;
import com.jdev.passwordManager.entity.GroupAccountEntity;
import com.jdev.passwordManager.exception.PasswordManagerRuntimeException;
import com.jdev.passwordManager.repository.GroupAccountRepository;
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

import static com.jdev.passwordManager.GroupAccountHelper.GROUP_ACCOUNT_NAME;
import static com.jdev.passwordManager.GroupAccountHelper.ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupAccountServiceTest {

    @InjectMocks
    private GroupAccountService groupAccountService;
    @Mock
    private GroupAccountRepository groupAccountRepository;

    @AfterEach
    private void mockitoAfterVerifyNoMoreInteractions() {
        verifyNoMoreInteractions(groupAccountRepository);
    }

    @Test
    void test_createGroupAccount() {
        GroupAccountEntity groupAccountEntity = GroupAccountEntity.builder().groupName(GROUP_ACCOUNT_NAME).build();
        when(groupAccountRepository.saveAndFlush(groupAccountEntity)).thenReturn(groupAccountEntity.toBuilder()
                .id(ID)
                .build());

        short actual = groupAccountService.createGroupAccount(GROUP_ACCOUNT_NAME);

        Assertions.assertEquals(ID, actual);

        verify(groupAccountRepository).saveAndFlush(groupAccountEntity);
    }

    @Test
    void test_getGroupAccountById_notFound() {
        when(groupAccountRepository.findById(ID)).thenReturn(Optional.empty());

        PasswordManagerRuntimeException passwordManagerRuntimeException = Assertions.assertThrows(
                PasswordManagerRuntimeException.class, () -> groupAccountService.getGroupAccountById(ID));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, passwordManagerRuntimeException.getHttpStatus());
        Assertions.assertEquals(CommonResponse.ErrorType.NOT_FOUND_ENTITY,
                passwordManagerRuntimeException.getErrorType());
        Assertions.assertEquals("not found group account by id - " + ID,
                passwordManagerRuntimeException.getErrorMessage());
    }

    @Test
    void test_getGroupAccountById() {
        when(groupAccountRepository.findById(ID)).thenReturn(Optional.of(GroupAccountEntity.builder()
                .groupName(GROUP_ACCOUNT_NAME)
                .build()));

        String result = groupAccountService.getGroupAccountById(ID);

        assertEquals(GROUP_ACCOUNT_NAME, result);
    }

    @Test
    void test_getAllGroupAccounts_emptyResult() {
        when(groupAccountRepository.findAll()).thenReturn(List.of());

        List<String> allGroupAccounts = groupAccountService.getGroupAccounts();

        assertEquals(List.of(), allGroupAccounts);
    }

    @Test
    void test_getAllGroupAccounts_Results() {
        when(groupAccountRepository.findAll()).thenReturn(List.of(GroupAccountEntity.builder()
                .groupName(GROUP_ACCOUNT_NAME)
                .build()));

        List<String> allGroupAccounts = groupAccountService.getGroupAccounts();

        assertEquals(List.of(GROUP_ACCOUNT_NAME), allGroupAccounts);
    }

    @Test
    void test_getGroupAccountByName() {
        Optional<GroupAccountEntity> optionalGroupAccountEntity = Optional.of(GroupAccountEntity.builder().build());
        when(groupAccountRepository.getGroupAccountEntityByName(GROUP_ACCOUNT_NAME)).thenReturn(
                optionalGroupAccountEntity);

        groupAccountService.getGroupAccountByName(GROUP_ACCOUNT_NAME);

        verify(groupAccountRepository).getGroupAccountEntityByName(GROUP_ACCOUNT_NAME);
    }


}