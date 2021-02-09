package com.kubel.controllers;

import com.kubel.exception.Forbidden;
import com.kubel.security.UserPrincipal;
import com.kubel.service.AccountService;
import com.kubel.service.dto.AccountDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;


@RestController
public class AccountController {

    private final AccountService accountService;


    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/user/registration")
    public ResponseEntity<AccountDto> registrationNewUser(@RequestBody @Valid final AccountDto accountDto, HttpServletRequest request) {
        String appUrl = request.getContextPath();

        return ResponseEntity.ok().body(accountService.registerNewUserAccount(accountDto, request.getLocale(), appUrl));
    }

    @GetMapping("/confirm")
    public ResponseEntity<Void> confirmRegistration(@RequestParam("token") String token, HttpServletRequest request) {
        accountService.confirmRegistration(token, request.getLocale());
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).location(URI.create("http://localhost:3000/user/login")).build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AccountDto> getUserById(@PathVariable Long id, Authentication auth) {
        var principal = (UserPrincipal) auth.getPrincipal();
        if (!principal.getId().equals(id)) {
            throw new Forbidden();
        }
        return ResponseEntity.ok().body(accountService.getUserById(id));
    }
}
