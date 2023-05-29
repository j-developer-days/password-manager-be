package com.jdev.passwordManager.repository;

import com.jdev.passwordManager.entity.GroupAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GroupAccountRepository extends JpaRepository<GroupAccountEntity, Short> {

    @Query("SELECT gae FROM GroupAccountEntity gae WHERE gae.groupName = ?1")
    Optional<GroupAccountEntity> getGroupAccountEntityByName(String groupName);

}
