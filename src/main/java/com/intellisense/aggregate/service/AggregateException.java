package com.intellisense.aggregate.service;

import org.springframework.http.HttpStatus;

public class AggregateException extends RuntimeException {
    private final HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

    public AggregateException(String s) {
        super(s);
    }
}
