package com.kubel.service.impl;

import com.kubel.entity.Account;
import com.kubel.entity.Ad;
import com.kubel.exception.ResourceNotFoundException;
import com.kubel.repo.AccountRepository;
import com.kubel.repo.AdRepository;
import com.kubel.repo.specification.AdSpecification;
import com.kubel.service.AdService;
import com.kubel.service.dto.AdDto;
import com.kubel.service.mapper.AdMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

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

    @Scheduled(cron = "* 0 0 * * ?", zone = "Europe/Minsk")
    private void checkValidityAd() {
        List<Ad> adList = adRepository.findAllByActiveTrue();
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Minsk"));
        if ((!adList.isEmpty()) || (adList != null)) {
            adList.forEach(ad -> {
                if (ad.getValidity().isEqual(today)) {
                    ad.setActive(false);
                    adRepository.save(ad);
                }
            });
        }
    }

    @Override
    public Page<AdDto> getAllAd(AdSpecification adSpecification, Pageable pageable) {
        Page<Ad> adPage = adRepository.findAll(adSpecification, pageable);
        return adPage.map(adMapper::toDto);
    }

    @Override
    public Page<AdDto> getAllAdByUserId(Long userId, AdSpecification adSpecification, Pageable pageable) {
        Page<Ad> adPage = adRepository.findAllByAccountId(userId, pageable);
        return adPage.map(adMapper::toDto);
    }
}
