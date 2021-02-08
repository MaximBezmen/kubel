package com.kubel.service.impl;


import com.kubel.entity.Account;
import com.kubel.entity.VerificationToken;
import com.kubel.exception.BadRequest;
import com.kubel.exception.ResourceNotFoundException;
import com.kubel.exception.UserAlreadyExistException;
import com.kubel.repo.AccountRepository;
import com.kubel.repo.VerificationTokenRepository;
import com.kubel.service.AccountService;
import com.kubel.service.OnRegistrationCompleteEvent;
import com.kubel.service.dto.AccountDto;
import com.kubel.service.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final VerificationTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, ApplicationEventPublisher eventPublisher, VerificationTokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.eventPublisher = eventPublisher;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
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
    public AccountDto registerNewUserAccount(AccountDto accountDto, Locale locale, String appUrl) {
        Optional<Account> accountOptional = accountRepository.findByEmail(accountDto.getEmail());
        if (accountOptional.isPresent()) {
            throw new UserAlreadyExistException(accountDto.getEmail());
        }
        Account accountEntity = accountMapper.toEntity(accountDto);
        accountEntity.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        accountEntity = accountRepository.save(accountEntity);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(accountEntity, locale, appUrl));
        return accountMapper.toDto(accountEntity);
    }

    @Override
    public AccountDto getUserById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new ResourceNotFoundException("");
        }
        return accountMapper.toDto(accountOptional.get());
    }

    @Override
    public void createVerificationToken(Account account, String token) {
        VerificationToken myToken = new VerificationToken();
        myToken.setToken(token);
        myToken.setAccount(account);
        tokenRepository.save(myToken);
    }

    @Override
    public void confirmRegistration(String token, Locale locale) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            throw new ResourceNotFoundException(token);
        }
        Account account = verificationToken.getAccount();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw new BadRequest("Срок действия токина истек.");
        }
        account.setEnabled(true);
        accountRepository.save(account);
    }

//    @Override
//    public AccountDto getUserLogin(AccountDto accountDto) {
//        return null;
//    }
}
