package com.jdev.passwordManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.jdev.passwordManager.config.BeanTestConfig;
import com.jdev.passwordManager.repository.AccountRepository;
import com.jdev.passwordManager.repository.GroupAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(BeanTestConfig.class)
public abstract class ParentTestHelper {

    @Autowired
    protected TestRestTemplate template;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected Faker faker;

    @Autowired
    private GroupAccountRepository groupAccountRepository;

    @Autowired
    private AccountRepository accountRepository;

    @LocalServerPort
    protected int port;

    private String url;

    protected String getUrl() {
        if (url == null) {
            url = "http://localhost:" + port + "/password-manager/";
        }
        return url;
    }

    protected abstract String getUrlWithRestControllerRequestMapping();

    @BeforeEach
    protected void setUp() {
        accountRepository.deleteAllInBatch();
        groupAccountRepository.deleteAllInBatch();
    }

}
