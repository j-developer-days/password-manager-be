package com.jdev.passwordManager.service;

import com.jdev.passwordManager.converter.AccountConverter;
import com.jdev.dto.request.AccountRequestDto;
import com.jdev.dto.response.AccountResponseDto;
import com.jdev.dto.response.CommonResponse;
import com.jdev.passwordManager.entity.AccountEntity;
import com.jdev.passwordManager.exception.PasswordManagerRuntimeException;
import com.jdev.passwordManager.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final GroupAccountService groupAccountService;

    @Transactional
    public Integer createAccount(AccountRequestDto accountRequestDto) {
        if (!accountRequestDto.getAccountPassword().equals(accountRequestDto.getAccountPasswordConfirm())) {
            throw new PasswordManagerRuntimeException(CommonResponse.ErrorType.NOT_EQUAL_PASSWORD, "password and " +
                    "confirm password is different!", HttpStatus.BAD_REQUEST);
        }
        AccountEntity accountEntity = AccountConverter.convertFromAccountRequestDtoToAccountEntity.apply(accountRequestDto);
        groupAccountService.getGroupAccountByName(accountEntity.getGroupAccountEntity().getGroupName()).ifPresent(accountEntity::setGroupAccountEntity);
        return accountRepository.saveAndFlush(accountEntity).getId();
    }

    @Transactional(readOnly = true)
    public List<AccountResponseDto> getAccounts() {
        return accountRepository.findAll().stream()
                .map(AccountConverter.convertFromAccountEntityToAccountResponseDto)
                .collect(Collectors.toList());
    }

}
