package com.kubel.service.dto;

import com.kubel.valid.ValidPhoneNumber;
import lombok.Data;

import java.time.LocalDate;

@Data
@ValidPhoneNumber
public class AdDto {

    private Long id;
    private String topic;
    private LocalDate dateOfPlacement;
    private LocalDate validity;
    private String content;
    private String city;
    private String phoneNumber;
    private Long price;
    private Long accountId;
}
