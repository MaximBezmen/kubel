package com.kubel.service;

import com.kubel.entity.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final AccountService accountService;

    private final JavaMailSender mailSender;
    @Value("${server.link}")
    private String server;

    public RegistrationListener(AccountService accountService, JavaMailSender mailSender) {
        this.accountService = accountService;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        Account user = event.getAccount();
        String token = UUID.randomUUID().toString();
        accountService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/confirm?token=" + token;
        //String message = messages.getMessage("message.regSucc", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("link: " + server + confirmationUrl);
        mailSender.send(email);
    }
}
