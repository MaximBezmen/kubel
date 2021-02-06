package com.kubel.kubel.service.mapper;

import com.kubel.kubel.entity.Account;
import com.kubel.kubel.service.dto.AccountDto;
import com.kubel.kubel.utils.InstantMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {InstantMapper.class, Account.class})
public interface AccountMapper {

    AccountDto toDto(Account entity);

    Account toEntity(AccountDto dto);
}
