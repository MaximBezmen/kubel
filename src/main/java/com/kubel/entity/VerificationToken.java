package com.kubel.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "verification_token_id_seq")
    @SequenceGenerator(name = "verification_token_id_seq", sequenceName = "verification_token_id_seq", allocationSize = 1)
    private Long id;
    private String token;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "account_id")
    Account account;
    @JoinColumn(nullable = false, name = "expiry_date")
    private Date expiryDate;

    @PrePersist
    private void calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, EXPIRATION);
        expiryDate = new Date(cal.getTime().getTime());
    }
}
