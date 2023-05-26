package com.jdev.passwordManager.restController;

import com.jdev.passwordManager.service.GroupAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Slf4j

@RequestMapping(value = "/group-account", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@RestController
public class GroupAccountRestController {

    private final GroupAccountService groupAccountService;

    @PostMapping("/{groupAccountName}")
    public ResponseEntity<String> createGroupAccount(@PathVariable String groupAccountName) {
        log.info("call create group account endpoint with groupAccountName=[{}]", groupAccountName);
        groupAccountService.createGroupAccount(groupAccountName);
        return ResponseEntity.created(URI.create("/")).build();
    }

    @GetMapping("/{groupAccountId}")
    public ResponseEntity<String> getGroupAccountById(@PathVariable Short groupAccountId) {
        log.info("call get group account endpoint by id with groupAccountId=[{}]", groupAccountId);
        String groupAccountById = groupAccountService.getGroupAccountById(groupAccountId);
        if (groupAccountById == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(groupAccountById);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<String>> getAllGroupAccount() {
        log.info("call get all group account endpoint");
        return ResponseEntity.ok(groupAccountService.getAllGroupAccounts());
    }

}
