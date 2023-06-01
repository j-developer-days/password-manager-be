package com.jdev.restController;

import com.jdev.RestAssuredProjectConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupAccountRestControllerIT extends RestAssuredProjectConfig {

    @Test
    void test_createGroupAccount() {
        Response response =
                RestAssured.post("/group-account/"
                        + RestAssuredProjectConfig.FAKER.internet().url());
        printResponse(response);
        Assertions.assertEquals(200, response.getStatusCode());
    }

}
