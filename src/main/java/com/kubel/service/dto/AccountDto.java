package com.kubel.service.dto;

import lombok.Data;

@Data
public class AccountDto {
    private Long id;
    private String userName;
    private String login;
    private String password;
}
