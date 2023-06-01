package com.jdev.passwordManager.config;

import com.jdev.passwordManager.dto.response.CommonResponse;
import com.jdev.passwordManager.exception.PasswordManagerRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@Slf4j

@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(PasswordManagerRuntimeException.class)
    ResponseEntity<CommonResponse<String>> passwordManagerRuntimeExceptionHandler(PasswordManagerRuntimeException e) {
        final String uuidForTrack = UUID.randomUUID().toString();
        log.error("-----CustomControllerAdvice.passwordManagerRuntimeExceptionHandler-----log uuid - [" + uuidForTrack + "]", e);
        return new ResponseEntity<>(CommonResponse.error(new CommonResponse.Error(e.getErrorType(), uuidForTrack,
                e.getErrorMessage())), e.getHttpStatus());
    }

}
