package com.kubel.repo;

import com.kubel.entity.Account;
import com.kubel.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

    VerificationToken findByAccount(Account account);
}
