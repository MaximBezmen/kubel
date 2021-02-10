package com.kubel.controllers;

import com.kubel.exception.Forbidden;
import com.kubel.security.UserPrincipal;
import com.kubel.service.MessageService;
import com.kubel.service.dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("user/{userId}/ads/{id}messages")
    public ResponseEntity<MessageDto> crateMessageByUserIdAndByAd(@PathVariable final Long userId, @PathVariable final Long id, @RequestBody final MessageDto dto, final Authentication auth) {
        var principal = (UserPrincipal) auth.getPrincipal();
        if (!principal.getId().equals(userId)){
            throw new Forbidden();
        }
        return ResponseEntity.ok().body(messageService.crateMessageByUserIdAndByAd(userId, id, dto));
    }
}
