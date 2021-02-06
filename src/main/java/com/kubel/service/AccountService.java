package com.kubel.service;

import com.kubel.service.dto.AccountDto;

public interface AccountService {
    AccountDto getUserByLoginAndPassword(AccountDto accountDto);

}
