package com.kubel.service.mapper;

import com.kubel.entity.Ad;
import com.kubel.service.dto.AdDto;
import com.kubel.utils.InstantMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {InstantMapper.class, AccountMapper.class})
public interface AdMapper {
    @Mapping(target = "id", ignore = true)
    Ad toEntity(AdDto dto);

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "photo", ignore = true)
    AdDto toDto(Ad entity);
}
