package com.kubel.service.impl;


import com.kubel.entity.Account;
import com.kubel.exception.ResourceNotFoundException;
import com.kubel.exception.UserAlreadyExistException;
import com.kubel.repo.AccountRepository;
import com.kubel.service.AccountService;
import com.kubel.service.dto.AccountDto;
import com.kubel.service.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDto getUserByLoginAndPassword(AccountDto accountDto) {
        Optional<Account> accountOptional = accountRepository.findByLoginAndPassword(accountDto.getLogin(), accountDto.getPassword());
        if (!accountOptional.isPresent()) {
            logger.info("User with not found.");
            throw new ResourceNotFoundException("User with not found.");
        }
        return accountMapper.toDto(accountOptional.get());
    }

    @Transactional
    @Override
    public AccountDto registerNewUserAccount(AccountDto accountDto) {
        Optional<Account> accountOptional = accountRepository.findByEmail(accountDto.getEmail());
        if (accountOptional.isPresent()) {
            throw new UserAlreadyExistException(accountDto.getEmail());
        }
        Account accountEntity = accountMapper.toEntity(accountDto);
        return accountMapper.toDto(accountRepository.save(accountEntity));
    }
}
