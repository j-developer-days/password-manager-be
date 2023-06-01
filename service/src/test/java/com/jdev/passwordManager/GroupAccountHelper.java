package com.jdev.passwordManager;

import com.jdev.passwordManager.entity.GroupAccountEntity;

public class GroupAccountHelper {

    public final static short ID = 1;
    public final static String GROUP_ACCOUNT_NAME = "TestMockito";

    public static GroupAccountEntity getGroupAccountEntity() {
        GroupAccountEntity groupAccountEntity = new GroupAccountEntity();
        groupAccountEntity.setGroupName(GROUP_ACCOUNT_NAME);
        return groupAccountEntity;
    }

}
