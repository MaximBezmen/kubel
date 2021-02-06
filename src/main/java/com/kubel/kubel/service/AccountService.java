package com.kubel.kubel.service;

import com.kubel.kubel.service.dto.AccountDto;

public interface AccountService {
    AccountDto getUserByLoginAndPassword(AccountDto accountDto);

}
