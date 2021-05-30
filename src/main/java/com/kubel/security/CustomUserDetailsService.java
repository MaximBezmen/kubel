package com.kubel.security;

import com.kubel.entity.Account;
import com.kubel.exception.BadRequestException;
import com.kubel.exception.ResourceNotFoundException;
import com.kubel.repository.AccountRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public CustomUserDetailsService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @SneakyThrows
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) {

        Optional<Account> accountOptional = accountRepository.findByLoginOrEmail(login, login);
        if (accountOptional.isEmpty()) {
            log.info("No user found with username: " + login);
            throw new UsernameNotFoundException("No user found with username: " + login);
        }

        if (!accountOptional.get().isEnabled()) {
            log.info("Account not activated");
            throw new BadRequestException("Account not activated");
        }

        return UserPrincipal.create(accountOptional.get());
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Account", "id", id)
        );
        return UserPrincipal.create(account);
    }
}
