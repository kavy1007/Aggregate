package com.intellisense.aggregate.service;

import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException {
    private final HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

    public APIException(String s) {
        super(s);
    }
}
