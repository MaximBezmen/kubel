package com.kubel.service;

import com.kubel.repo.specification.AdSpecification;
import com.kubel.service.dto.AdDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdService {
    AdDto crateAdByUserId(Long id, AdDto adDto);

    Page<AdDto> getAllAd(AdSpecification adSpecification, Pageable pageable);

    Page<AdDto> getAllAdByUserId(Long userId, AdSpecification adSpecification, Pageable pageable);

    void deleteAdById(Long adId, Long userId);
}
