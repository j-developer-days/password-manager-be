package com.jdev.passwordManager.repository;

import com.jdev.passwordManager.entity.GroupAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupAccountRepository extends JpaRepository<GroupAccountEntity, Short> {
}
