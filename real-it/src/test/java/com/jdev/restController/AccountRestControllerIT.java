package com.jdev.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jdev.RestAssuredProjectConfig;
import com.jdev.passwordManager.dto.request.AccountRequestDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
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
                .header(HttpHeaders.CONTENT_TYPE, ContentType.JSON)
                .body(requestBody)
                .post("/account/");
        printResponse(response);

        Assertions.assertEquals(200, response.getStatusCode());
    }

}
