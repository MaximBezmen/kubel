package com.kubel.service;

import com.kubel.service.dto.AdDto;

public interface AdService {
    AdDto crateAdByUserId(Long id, AdDto adDto);

}
