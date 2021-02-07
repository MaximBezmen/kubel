package com.kubel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistException extends RuntimeException {
    private String resourceName;

    public UserAlreadyExistException(String resourceName) {
        super(String.format("User with %s email already exists.", resourceName));
        this.resourceName = resourceName;
    }

}
