package com.kubel.valid.impl;

import com.kubel.service.dto.AdDto;
import com.kubel.valid.ValidPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, Object> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String PHONE_NUMBER_PATTERN = "^\\+375\\((17|29|33|44)\\)-[0-9]{3}-[0-9]{2}-[0-9]{2}$";

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        AdDto adDto = (AdDto) value;
        matcher = pattern.matcher(adDto.getPhoneNumber());
        return matcher.matches();
    }
}
