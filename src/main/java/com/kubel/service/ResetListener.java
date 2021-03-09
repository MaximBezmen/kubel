package com.kubel.service;

import com.kubel.entity.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResetListener implements ApplicationListener<OnResetPasswordEven> {

    private final AccountService accountService;

    private final JavaMailSender mailSender;
    @Value("${server.link}")
    private String server;

    public ResetListener(AccountService accountService, JavaMailSender mailSender) {
        this.accountService = accountService;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnResetPasswordEven event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnResetPasswordEven event) {
        Account user = event.getAccount();
        String token = UUID.randomUUID().toString();
        accountService.createPasswordResetTokenForUser(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Reset Password Confirmation";
        String resetUrl = server + "/user/changePassword?token=" + token;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("Reset password on the site \"Plushkin\" follow the link: " + resetUrl);
        mailSender.send(email);
    }
}

