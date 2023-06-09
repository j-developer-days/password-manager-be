package com.jdev.passwordManager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommonResponse<T> {

    private T body;
    private Error error;

    private CommonResponse(T body) {
        this.body = body;
    }

    private CommonResponse(Error error) {
        this.error = error;
    }

    public static <T> CommonResponse<T> success(T body) {
        return new CommonResponse<>(body);
    }

    public static CommonResponse<String> error(Error error) {
        return new CommonResponse<>(error);
    }

    public enum ErrorType {
        NOT_EQUAL_PASSWORD, NOT_FOUND_ENTITY;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Error {
        private ErrorType errorType;
        private String uuid;
        private String errorMessage;
    }

}
