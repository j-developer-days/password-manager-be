package com.jdev.passwordManager.repository;

import com.jdev.passwordManager.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
}
