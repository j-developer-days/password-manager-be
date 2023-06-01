package com.jdev.passwordManager.service;

import com.jdev.passwordManager.dto.response.CommonResponse;
import com.jdev.passwordManager.entity.GroupAccountEntity;
import com.jdev.passwordManager.exception.PasswordManagerRuntimeException;
import com.jdev.passwordManager.repository.GroupAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j

@Service
public class GroupAccountService {

    private final GroupAccountRepository groupAccountRepository;

    @Transactional
    public short createGroupAccount(String groupAccountName) {
        GroupAccountEntity groupAccountEntity = groupAccountRepository.saveAndFlush(GroupAccountEntity.builder().groupName(groupAccountName).build());
        log.debug("created new group account - [{}]", groupAccountEntity);
        return groupAccountEntity.getId();
    }

    @Transactional(readOnly = true)
    public String getGroupAccountById(Short id) {
        Optional<GroupAccountEntity> optionalGroupAccountEntity = groupAccountRepository.findById(id);
        if (optionalGroupAccountEntity.isEmpty()) {
            throw new PasswordManagerRuntimeException(CommonResponse.ErrorType.NOT_FOUND_ENTITY,
                    "not found group account by id - " + id, HttpStatus.NOT_FOUND);
        }
        return optionalGroupAccountEntity.get().getGroupName();
    }

    @Transactional(readOnly = true)
    public List<String> getGroupAccounts() {
        return groupAccountRepository.findAll().stream().map(GroupAccountEntity::getGroupName).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<GroupAccountEntity> getGroupAccountByName(String groupName) {
        return groupAccountRepository.getGroupAccountEntityByName(groupName);
    }

}
