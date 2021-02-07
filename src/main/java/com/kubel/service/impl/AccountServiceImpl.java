package com.kubel.service.impl;


import com.kubel.entity.Account;
import com.kubel.exception.ResourceNotFoundException;
import com.kubel.service.AccountService;
import com.kubel.service.mapper.AccountMapper;
import com.kubel.repo.AccountRepository;
import com.kubel.service.dto.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository usersRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.usersRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDto getUserByLoginAndPassword(AccountDto accountDto) {
        Optional<Account> userOptional = usersRepository.findByLoginAndPassword(accountDto.getLogin(), accountDto.getPassword());
        if (!userOptional.isPresent()) {
            logger.info("User with not found.");
            throw new ResourceNotFoundException("User with not found.");
        }
        return accountMapper.toDto(userOptional.get());
    }
}
