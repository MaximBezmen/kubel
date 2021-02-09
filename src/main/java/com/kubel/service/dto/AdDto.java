package com.kubel.service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdDto {

    private Long id;
    private String topic;
    private LocalDate dateOfPlacement;
    private LocalDate validity;
    private String content;
    private String city;
    private Long accountId;
}
