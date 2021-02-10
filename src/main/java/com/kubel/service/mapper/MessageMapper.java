package com.kubel.service.mapper;

import com.kubel.entity.Message;
import com.kubel.service.dto.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MessageMapper {

    @Mapping(target = "adId", source = "ad.id")
    @Mapping(target = "accountId", source = "account.id")
    MessageDto toDto(Message entity);

    @Mapping(target = "id", ignore = true)
    Message toEntity(MessageDto dto);
}
