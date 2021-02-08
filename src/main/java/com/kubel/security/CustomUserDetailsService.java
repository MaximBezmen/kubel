package com.kubel.security;

import com.kubel.entity.Account;
import com.kubel.exception.BadRequest;
import com.kubel.repo.AccountRepository;
import lombok.SneakyThrows;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public CustomUserDetailsService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @SneakyThrows
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {

        Optional<Account> accountOptional = accountRepository.findByLogin(email);
        if (accountOptional.isEmpty()) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        if (!accountOptional.get().isEnabled()) {
            throw new BadRequest("Профиль не активирован.");
        }
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new UserPrincipal(accountOptional.get().getId(),accountOptional.get().getEmail(),accountOptional.get().getPassword(), authorities);
    }

}
