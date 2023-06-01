package com.jdev.passwordManager.restController;

import com.jdev.passwordManager.dto.request.AccountRequestDto;
import com.jdev.passwordManager.dto.response.AccountResponseDto;
import com.jdev.passwordManager.dto.response.CommonResponse;
import com.jdev.passwordManager.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j

@RequestMapping(value = "/account/", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@RestController
public class AccountRestController {

    private final AccountService accountService;

    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<Integer>> createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        log.info("call create account endpoint, request body - [{}]", accountRequestDto);
        return ResponseEntity.ok(CommonResponse.success(accountService.createAccount(accountRequestDto)));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<AccountResponseDto>>> getAccounts() {
        log.info("call get all accounts endpoint");
        return ResponseEntity.ok(CommonResponse.success(accountService.getAccounts()));
    }

}
