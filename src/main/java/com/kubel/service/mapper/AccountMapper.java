package com.kubel.service.mapper;

import com.kubel.entity.Account;
import com.kubel.service.dto.AccountDto;
import com.kubel.utils.InstantMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {InstantMapper.class, Account.class})
public interface AccountMapper {

    AccountDto toDto(Account entity);

    Account toEntity(AccountDto dto);
}
