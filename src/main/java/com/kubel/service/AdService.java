package com.kubel.service;

import com.kubel.repo.specification.AdSpecification;
import com.kubel.service.dto.AdDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdService {
    AdDto crateAdByUserId(Long id, AdDto adDto) throws IOException;

    Page<AdDto> getAllAd(AdSpecification adSpecification, Pageable pageable);

    Page<AdDto> getAllAdByUserId(Long userId, AdSpecification adSpecification, Pageable pageable);

    void deleteAdById(Long adId, Long userId);
}
