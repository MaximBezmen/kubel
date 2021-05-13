package com.kubel.controllers;

import com.kubel.exception.ForbiddenException;
import com.kubel.security.UserPrincipal;
import com.kubel.service.AccountService;
import com.kubel.service.dto.AccountDto;
import com.kubel.service.dto.PasswordDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @Value("${server.front}")
    private String server;


    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/users/registration")
    public ResponseEntity<AccountDto> registrationNewUser(@RequestBody @Valid final AccountDto accountDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(accountService.registerNewUserAccount(accountDto));
    }

    @GetMapping("/confirm")
    public ResponseEntity<Void> confirmRegistration(@RequestParam("token") String token) {
        accountService.confirmRegistration(token);
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).location(URI.create(server + "/sign_in")).build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AccountDto> getUserById(@PathVariable Long id, Authentication auth) {
        var principal = (UserPrincipal) auth.getPrincipal();
        if (!principal.getId().equals(id)) {
            throw new ForbiddenException();
        }
        return ResponseEntity.ok().body(accountService.getUserById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<AccountDto>> getAllUsersForAdmin(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(accountService.getAllUsersForAdmin(pageable));
    }

    @GetMapping("/user/resetPassword")
    public ResponseEntity<Void> resetPassword(@RequestParam String email) {
        accountService.resetPassword(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/changePassword")
    public ResponseEntity<String> confirmChangePassword(@RequestParam String token) {
        accountService.confirmChangePassword(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/savePassword")
    public ResponseEntity<Void> saveNewPassword(@RequestBody PasswordDto passwordDto, Authentication auth) {
        var principal = (UserPrincipal) auth.getPrincipal();
        accountService.saveNewPassword(passwordDto, principal.getId());
        return ResponseEntity.ok().build();
    }
}
