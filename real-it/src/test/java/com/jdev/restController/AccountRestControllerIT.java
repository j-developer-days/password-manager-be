package com.jdev.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jdev.RestAssuredProjectConfig;
import com.jdev.dto.request.AccountRequestDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountRestControllerIT extends RestAssuredProjectConfig {

    @Test
    void test_createGroupAccount() throws JsonProcessingException {
        final String requestBody = OBJECT_MAPPER.writeValueAsString(AccountRequestDto.builder()
                .accountName(FAKER.name().name())
                .accountURL(FAKER.internet().url())
                .accountGroupName("email")
                .accountPassword("12345")
                .accountPasswordConfirm("12345")
                .build());
        print(requestBody);
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/account/");
        printResponse(response);

        Assertions.assertEquals(200, response.getStatusCode());
    }

}
