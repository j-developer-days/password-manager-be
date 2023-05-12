package com.jdev;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredProjectConfig {

    public static final Faker FAKER = new Faker();

    static {
        RestAssured.baseURI = "http://127.0.0.1";
        RestAssured.basePath = "/password-manager";
        RestAssured.port = 10101;
    }

    protected void printResponse(Response response) {
        System.out.println("Headers: " + response.getHeaders() + "\n\n");
        System.out.println("Body - " + response.getBody().prettyPrint());
    }

}
