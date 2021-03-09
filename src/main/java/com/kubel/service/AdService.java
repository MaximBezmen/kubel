package com.kubel.service;

import com.kubel.service.dto.AdDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdService {
    AdDto crateAdByUserId(Long id, AdDto adDto);

    Page<AdDto> getAllAd(Pageable pageable);
}
