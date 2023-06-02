package com.jdev.passwordManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.jdev.passwordManager.config.BeanTestConfig;
import com.jdev.passwordManager.repository.AccountRepository;
import com.jdev.passwordManager.repository.GroupAccountRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(BeanTestConfig.class)
@Testcontainers
@ActiveProfiles("it")
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

    @Container
    protected final static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse(
            "postgres:15"));

    @BeforeAll
    public static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    public static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

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
