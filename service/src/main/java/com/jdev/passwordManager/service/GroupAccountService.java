package com.jdev.passwordManager.service;

import com.jdev.passwordManager.entity.GroupAccountEntity;
import com.jdev.passwordManager.repository.GroupAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void createGroupAccount(String groupAccountName) {
        GroupAccountEntity groupAccountEntity = groupAccountRepository.saveAndFlush(GroupAccountEntity.builder().groupName(groupAccountName).build());
        log.debug("created new group account - [{}]", groupAccountEntity);
    }

    @Transactional(readOnly = true)
    public String getGroupAccountById(Short id) {
        Optional<GroupAccountEntity> byId = groupAccountRepository.findById(id);
        return byId.isEmpty() ? null : byId.get().getGroupName();
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
