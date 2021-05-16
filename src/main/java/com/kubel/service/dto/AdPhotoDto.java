package com.kubel.service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdPhotoDto {
    private Long id;
    private String topic;
    private LocalDate dateOfPlacement;
    private LocalDate validity;
    private String content;
    private String city;
    private String phoneNumber;
    private Long price;
    private Long accountId;
    private byte[] photo;
}