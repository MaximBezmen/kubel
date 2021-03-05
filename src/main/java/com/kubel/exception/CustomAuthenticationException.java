package com.kubel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CustomAuthenticationException extends AuthenticationException {
    public CustomAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomAuthenticationException() {
        super("Unauthorized");
    }
}
