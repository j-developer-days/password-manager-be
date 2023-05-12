package com.jdev;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupAccountRestControllerIT {

    @Test
    void test_createGroupAccount() {
        Response response = RestAssured.post("http://localhost:10101/password-manager/group-account/istore");
        Assertions.assertEquals(201, response.getStatusCode());
    }

}
