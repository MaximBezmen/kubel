package com.kubel.service.mapper;

import com.kubel.entity.Account;
import com.kubel.service.dto.AccountDto;
import com.kubel.utils.InstantMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {InstantMapper.class})
public interface AccountMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "matchingPassword", ignore = true)
    @Mapping(target = "role", source = "role.roleName")
    AccountDto toDto(Account entity);

    @Mapping(target = "id", ignore = true)
    Account toEntity(AccountDto dto);
}
