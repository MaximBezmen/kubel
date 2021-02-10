package com.kubel.service.dto;

import com.kubel.entity.Account;
import com.kubel.entity.Ad;
import lombok.Data;

@Data
public class MessageDto {
    private Long id;
    private String message;
    private Ad adId;
    private Account accountId;
}
