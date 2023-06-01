package com.jdev.passwordManager.converter;

import com.jdev.passwordManager.dto.request.AccountRequestDto;
import com.jdev.passwordManager.dto.response.AccountResponseDto;
import com.jdev.passwordManager.entity.AccountEntity;
import com.jdev.passwordManager.entity.GroupAccountEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountConverter {

    public static final Function<AccountEntity, AccountResponseDto> convertFromAccountEntityToAccountResponseDto =
            accountEntity -> AccountResponseDto.builder()
                    .accountName(accountEntity.getAccountName())
                    .accountURL(accountEntity.getAccountURL())
                    .accountPassword(accountEntity.getAccountPassword())
                    .creationZonedDateTime(accountEntity.getCreationZonedDateTime())
                    .accountGroupName(accountEntity.getGroupAccountEntity().getGroupName())
                    .build();

    public static final Function<AccountRequestDto, AccountEntity> convertFromAccountRequestDtoToAccountEntity =
            accountRequestDto -> {
                AccountEntity accountEntity = new AccountEntity();
                accountEntity.setAccountName(accountRequestDto.getAccountName());
                accountEntity.setAccountURL(accountRequestDto.getAccountURL());
                accountEntity.setAccountPassword(accountRequestDto.getAccountPassword());
                accountEntity.setGroupAccountEntity(GroupAccountEntity.builder().groupName(accountRequestDto.getAccountGroupName()).build());
                return accountEntity;
            };

}
