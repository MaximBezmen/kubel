package com.kubel.service;

import com.kubel.entity.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SendNewPasswordEven extends ApplicationEvent {
    private Account account;
    private String newPassword;

    public SendNewPasswordEven(Account account,  String newPassword) {
        super(account);
        this.account = account;
        this.newPassword = newPassword;
    }
}
