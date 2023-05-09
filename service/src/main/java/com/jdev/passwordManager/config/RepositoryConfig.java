package com.jdev.passwordManager.config;

import com.jdev.passwordManager.repository.AccountRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = AccountRepository.class)
public class RepositoryConfig {
}
