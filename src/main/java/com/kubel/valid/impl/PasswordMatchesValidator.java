package com.kubel.valid.impl;

import com.kubel.service.dto.AccountDto;
import com.kubel.valid.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        AccountDto user = (AccountDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
