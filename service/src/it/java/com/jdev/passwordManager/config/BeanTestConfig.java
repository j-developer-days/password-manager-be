package com.jdev.passwordManager.config;

import com.github.javafaker.Faker;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class BeanTestConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }

}
