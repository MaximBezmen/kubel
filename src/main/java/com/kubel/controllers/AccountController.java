package com.kubel.controllers;

import com.kubel.service.AccountService;
import com.kubel.service.dto.AccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class AccountController {

    private final AccountService accountService;

    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/registration/users")
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid final AccountDto accountDto) {

        return ResponseEntity.ok().body(accountService.registerNewUserAccount(accountDto));
    }
}
