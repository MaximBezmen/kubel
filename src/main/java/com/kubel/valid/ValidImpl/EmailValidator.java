package com.kubel.valid.ValidImpl;

import com.kubel.service.dto.AccountDto;
import com.kubel.valid.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator implements ConstraintValidator<ValidEmail, Object> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+](.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        AccountDto accountDto = (AccountDto) obj;
        matcher = pattern.matcher(accountDto.getEmail());
        return matcher.matches();
    }

}
