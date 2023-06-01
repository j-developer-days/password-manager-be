package com.jdev.passwordManager;

import com.jdev.passwordManager.dto.request.AccountRequestDto;
import com.jdev.passwordManager.dto.response.AccountResponseDto;
import com.jdev.passwordManager.entity.AccountEntity;
import com.jdev.passwordManager.entity.GroupAccountEntity;

import java.time.ZonedDateTime;

public class AccountHelper {

    public final static int ID = 1;
    private final static String ACCOUNT_NAME = CommonHelper.FAKER.name().name();
    private final static String ACCOUNT_URL = CommonHelper.FAKER.internet().url();
    private final static String ACCOUNT_PASSWORD = "12345";
    private final static ZonedDateTime ACCOUNT_CREATION = ZonedDateTime.now();

    public static AccountEntity getAccountEntity() {
        GroupAccountEntity groupAccountEntity = new GroupAccountEntity();
        groupAccountEntity.setGroupName(GroupAccountHelper.GROUP_ACCOUNT_NAME);
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountURL(ACCOUNT_URL);
        accountEntity.setAccountName(ACCOUNT_NAME);
        accountEntity.setAccountPassword(ACCOUNT_PASSWORD);
        accountEntity.setGroupAccountEntity(groupAccountEntity);
        return accountEntity;
    }


    public static AccountRequestDto getAccountRequestDto() {
        return AccountRequestDto.builder()
                .accountName(ACCOUNT_NAME)
                .accountURL(ACCOUNT_URL)
                .accountGroupName(GroupAccountHelper.GROUP_ACCOUNT_NAME)
                .accountPassword(ACCOUNT_PASSWORD)
                .accountPasswordConfirm(ACCOUNT_PASSWORD)
                .build();
    }

    public static AccountResponseDto getAccountResponseDto() {
        return AccountResponseDto.builder()
                .accountName(ACCOUNT_NAME)
                .accountURL(ACCOUNT_URL)
                .accountGroupName(GroupAccountHelper.GROUP_ACCOUNT_NAME)
                .accountPassword(ACCOUNT_PASSWORD)
                .creationZonedDateTime(ACCOUNT_CREATION)
                .build();
    }

}
