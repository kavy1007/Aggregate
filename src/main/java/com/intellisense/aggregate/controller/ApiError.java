package com.intellisense.aggregate.controller;

import lombok.Getter;

import java.time.OffsetDateTime;


@Getter
public class ApiError {

    private OffsetDateTime timestamp;
    private String message;
    private String desc;

    ApiError(String message, String desc) {

        this.message = message;
        this.desc = desc;
        this.timestamp = OffsetDateTime.now();
    }
}
