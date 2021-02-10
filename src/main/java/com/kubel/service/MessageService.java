package com.kubel.service;

import com.kubel.service.dto.MessageDto;

public interface MessageService {
    MessageDto crateMessageByUserIdAndByAd(Long userId, Long adId, MessageDto messageDto);
}
