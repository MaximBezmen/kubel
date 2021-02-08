package com.kubel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadRequest extends RuntimeException {
    private String resourceName;

    public BadRequest(String resourceName) {
        this.resourceName = resourceName;
    }

}