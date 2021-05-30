package com.kubel.service;

import com.kubel.repository.specification.AdSpecification;
import com.kubel.service.dto.AdDto;
import com.kubel.service.dto.AdPhotoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface AdService {
    AdDto crateAdByUserId(Long id, AdDto adDto) throws IOException;

    Page<AdDto> getAllAd(AdSpecification adSpecification, Pageable pageable);

    Page<AdDto> getAllAdByUserId(Long userId, AdSpecification adSpecification, Pageable pageable);

    void deleteAdById(Long adId, Long userId);

    AdPhotoDto getAdById(Long id) throws IOException;
}
