package com.jdev;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredProjectConfig {

    public static final Faker FAKER = new Faker();
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        RestAssured.baseURI = "http://127.0.0.1";
        RestAssured.basePath = "/password-manager";
        RestAssured.port = 10101;
    }

    protected void printResponse(Response response) {
        System.out.println("Headers: " + response.getHeaders() + "\n\n");
        System.out.println("Body - " + response.getBody().prettyPrint());
    }

    protected void print(Object o) {
        System.out.println(o);
    }

}
