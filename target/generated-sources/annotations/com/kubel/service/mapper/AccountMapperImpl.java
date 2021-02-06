package com.kubel.service.mapper;

import com.kubel.entity.Account;
import com.kubel.service.dto.AccountDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-06T19:01:37+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 14 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDto toDto(Account entity) {
        if ( entity == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setId( entity.getId() );
        accountDto.setUserName( entity.getUserName() );
        accountDto.setLogin( entity.getLogin() );
        accountDto.setPassword( entity.getPassword() );

        return accountDto;
    }

    @Override
    public Account toEntity(AccountDto dto) {
        if ( dto == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( dto.getId() );
        account.setUserName( dto.getUserName() );
        account.setLogin( dto.getLogin() );
        account.setPassword( dto.getPassword() );

        return account;
    }
}
