package com.jdev.passwordManager.exception;

import com.jdev.dto.response.CommonResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class PasswordManagerRuntimeException extends RuntimeException {

    private CommonResponse.ErrorType errorType;
    private String errorMessage;
    private HttpStatus httpStatus;

    public PasswordManagerRuntimeException(CommonResponse.ErrorType errorType, String errorMessage, HttpStatus httpStatus) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public PasswordManagerRuntimeException() {
    }

    public PasswordManagerRuntimeException(String message) {
        super(message);
    }

    public PasswordManagerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordManagerRuntimeException(Throwable cause) {
        super(cause);
    }

    public PasswordManagerRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
