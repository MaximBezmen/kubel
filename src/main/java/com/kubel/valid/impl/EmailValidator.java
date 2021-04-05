package com.kubel.valid.impl;

import com.kubel.service.dto.AccountDto;
import com.kubel.valid.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator implements ConstraintValidator<ValidEmail, Object> {


    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        // Do nothing
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        AccountDto accountDto = (AccountDto) obj;
        Matcher matcher = pattern.matcher(accountDto.getEmail());
        return matcher.matches();
    }

}
