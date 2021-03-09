package com.kubel.service;

import com.kubel.entity.Account;
import com.kubel.service.dto.AccountDto;
import com.kubel.service.dto.PasswordDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Locale;

public interface AccountService {

    AccountDto getUserByLoginAndPassword(AccountDto accountDto);

    AccountDto registerNewUserAccount(AccountDto accountDto);

    AccountDto getUserById(Long id);

    void createVerificationToken(Account account, String token);

    void confirmRegistration(String token);

    Page<AccountDto> getAllUsersForAdmin(Pageable pageable);

    String resetPassword(String email);

    void createPasswordResetTokenForUser(Account user, String token);

    String confirmChangePassword(String token);

    void saveNewPassword(PasswordDto passwordDto);
}
