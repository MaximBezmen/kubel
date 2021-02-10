package com.kubel.service.impl;

import com.kubel.entity.Account;
import com.kubel.entity.Ad;
import com.kubel.exception.ResourceNotFoundException;
import com.kubel.repo.AccountRepository;
import com.kubel.repo.AdRepository;
import com.kubel.service.AdService;
import com.kubel.service.dto.AdDto;
import com.kubel.service.mapper.AdMapper;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl implements AdService {

    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final AccountRepository accountRepository;

    public AdServiceImpl(AdMapper adMapper, AdRepository adRepository, AccountRepository accountRepository) {
        this.adMapper = adMapper;
        this.adRepository = adRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public AdDto crateAdByUserId(Long id, AdDto dto) {
        Ad adEntity = adMapper.toEntity(dto);
        Account accountEntity = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Account", "id", id));
        adEntity.setAccount(accountEntity);
        return adMapper.toDto(adRepository.save(adEntity));
    }
}
