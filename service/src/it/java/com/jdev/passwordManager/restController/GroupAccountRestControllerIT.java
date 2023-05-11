package com.jdev.passwordManager.restController;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static com.jdev.passwordManager.GroupAccountHelper.GROUP_ACCOUNT_NAME;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupAccountRestControllerIT {

    @Autowired
    private TestRestTemplate template;

    @LocalServerPort
    private int port;

    @Test
    void test_createGroupAccount() {
        ResponseEntity<String> response = template.postForEntity("http://localhost:" + port + "/password-manager/group-account/" +
                GROUP_ACCOUNT_NAME, null, String.class);
        Assertions.assertThat(response.getBody()).isNull();
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

}