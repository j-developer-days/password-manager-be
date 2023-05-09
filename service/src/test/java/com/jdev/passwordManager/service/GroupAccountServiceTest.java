package com.jdev.passwordManager.service;

import com.jdev.passwordManager.entity.GroupAccountEntity;
import com.jdev.passwordManager.repository.GroupAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupAccountServiceTest {

    private final static short ID = 1;
    private final static String GROUP_ACCOUNT_NAME = "TestMockito";

    @InjectMocks
    private GroupAccountService groupAccountService;
    @Mock
    private GroupAccountRepository groupAccountRepository;

    @Test
    void test_createGroupAccount() {
        GroupAccountEntity groupAccountEntity = GroupAccountEntity.builder().groupName(GROUP_ACCOUNT_NAME).build();
        when(groupAccountRepository.saveAndFlush(groupAccountEntity)).thenReturn(groupAccountEntity.toBuilder().id(ID).build());

        groupAccountService.createGroupAccount(GROUP_ACCOUNT_NAME);

        verify(groupAccountRepository).saveAndFlush(groupAccountEntity);
    }

    @Test
    void test_getGroupAccountById_emptyResult() {
        when(groupAccountRepository.findById(ID)).thenReturn(Optional.empty());

        String result = groupAccountService.getGroupAccountById(ID);

        assertNull(result);
    }

    @Test
    void test_getGroupAccountById() {
        when(groupAccountRepository.findById(ID)).thenReturn(Optional.of(GroupAccountEntity.builder().groupName(GROUP_ACCOUNT_NAME).build()));

        String result = groupAccountService.getGroupAccountById(ID);

        assertEquals(GROUP_ACCOUNT_NAME, result);
    }

    @Test
    void test_getAllGroupAccounts_emptyResult() {
        when(groupAccountRepository.findAll()).thenReturn(List.of());

        List<String> allGroupAccounts = groupAccountService.getAllGroupAccounts();

        assertEquals(List.of(), allGroupAccounts);
    }

    @Test
    void test_getAllGroupAccounts_Results() {
        when(groupAccountRepository.findAll()).thenReturn(List.of(GroupAccountEntity.builder().groupName(GROUP_ACCOUNT_NAME).build()));

        List<String> allGroupAccounts = groupAccountService.getAllGroupAccounts();

        assertEquals(List.of(GROUP_ACCOUNT_NAME), allGroupAccounts);
    }

}