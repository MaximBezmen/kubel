package com.kubel.valid.ValidImpl;

import com.kubel.service.dto.AdDto;
import com.kubel.valid.ValidPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, Object> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String PHONE_NUMBER_PATTERN = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";

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
