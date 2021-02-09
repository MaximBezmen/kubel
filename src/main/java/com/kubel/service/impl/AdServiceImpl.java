package com.kubel.service.impl;

import com.kubel.entity.Ad;
import com.kubel.repo.AdRepository;
import com.kubel.service.AdService;
import com.kubel.service.dto.AdDto;
import com.kubel.service.mapper.AdMapper;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl implements AdService {

    private final AdMapper adMapper;
    private final AdRepository adRepository;

    public AdServiceImpl(AdMapper adMapper, AdRepository adRepository) {
        this.adMapper = adMapper;
        this.adRepository = adRepository;
    }

    @Override
    public AdDto crateAdByUserId(Long id, AdDto dto) {
        Ad adEntity = adMapper.toEntity(dto);
        return adMapper.toDto(adRepository.save(adEntity));
    }
}
