package com.jdev.passwordManager.restController;

import com.jdev.passwordManager.dto.response.CommonResponse;
import com.jdev.passwordManager.service.GroupAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j

@RequestMapping(value = "/group-account/", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@RestController
public class GroupAccountRestController {

    private final GroupAccountService groupAccountService;

    @PostMapping("{groupAccountName}")
    public ResponseEntity<CommonResponse<Short>> createGroupAccount(@PathVariable String groupAccountName) {
        log.info("call create group account endpoint with groupAccountName=[{}]", groupAccountName);
        return ResponseEntity.ok(CommonResponse.success(groupAccountService.createGroupAccount(groupAccountName)));
    }

    @GetMapping("{groupAccountId}")
    public ResponseEntity<CommonResponse<String>> getGroupAccountById(@PathVariable Short groupAccountId) {
        log.info("call get group account endpoint by id with groupAccountId=[{}]", groupAccountId);
        return ResponseEntity.ok(CommonResponse.success(groupAccountService.getGroupAccountById(groupAccountId)));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<String>>> getGroupAccounts() {
        log.info("call get all group account endpoint");
        return ResponseEntity.ok(CommonResponse.success(groupAccountService.getGroupAccounts()));
    }

}
