package com.kubel.service;

import com.kubel.entity.Account;
import com.kubel.service.dto.AccountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Locale;

public interface AccountService {

    AccountDto getUserByLoginAndPassword(AccountDto accountDto);

    AccountDto registerNewUserAccount(AccountDto accountDto, Locale locale, String appUrl);

    AccountDto getUserById(Long id);

    void createVerificationToken(Account account, String token);

    void confirmRegistration(String token);

    Page<AccountDto> getAllUsersForAdmin(Pageable pageable);

//    AccountDto getUserLogin(AccountDto accountDto);
}
