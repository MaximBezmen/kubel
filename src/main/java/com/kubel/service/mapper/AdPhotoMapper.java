package com.kubel.service.mapper;

import com.kubel.entity.Ad;
import com.kubel.service.dto.AdPhotoDto;
import com.kubel.utils.InstantMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {InstantMapper.class, AccountMapper.class})
public interface AdPhotoMapper {
    @Mapping(target = "id", ignore = true)
    Ad toEntity(AdPhotoDto dto);

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "photo", ignore = true)
    AdPhotoDto toDto(Ad entity);
}
