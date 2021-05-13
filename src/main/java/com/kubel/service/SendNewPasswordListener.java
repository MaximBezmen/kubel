package com.kubel.service;

import com.kubel.entity.Account;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class SendNewPasswordListener implements ApplicationListener<SendNewPasswordEven> {


    private final JavaMailSender mailSender;

    public SendNewPasswordListener(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(SendNewPasswordEven event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(SendNewPasswordEven event) {
        Account user = event.getAccount();

        String recipientAddress = user.getEmail();
        String subject = "New password for Plushkin";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("Your new password: " + event.getNewPassword());
        mailSender.send(email);
    }
}
