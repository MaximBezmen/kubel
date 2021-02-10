package com.kubel.service.impl;

import com.kubel.entity.Account;
import com.kubel.entity.Ad;
import com.kubel.entity.Message;
import com.kubel.exception.ResourceNotFoundException;
import com.kubel.repo.AccountRepository;
import com.kubel.repo.AdRepository;
import com.kubel.repo.MessageRepository;
import com.kubel.service.MessageService;
import com.kubel.service.dto.MessageDto;
import com.kubel.service.mapper.MessageMapper;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final AccountRepository accountRepository;
    public final MessageRepository messageRepository;
    private final AdRepository adRepository;
    private final MessageMapper messageMapper;


    public MessageServiceImpl(AccountRepository accountRepository, MessageRepository messageRepository, AdRepository adRepository, MessageMapper messageMapper) {
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
        this.adRepository = adRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public MessageDto crateMessageByUserIdAndByAd(Long userId, Long adId, MessageDto dto) {
        Account accountEntity = accountRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("Account", "id", userId));
        Ad adEntity = adRepository.findById(adId).orElseThrow(() ->
                new ResourceNotFoundException("Ad", "id", adId));
        Message messageEntity = messageMapper.toEntity(dto);
        messageEntity.setAd(adEntity);
        messageEntity.setAccount(accountEntity);
        return messageMapper.toDto(messageRepository.save(messageEntity));
    }
}
