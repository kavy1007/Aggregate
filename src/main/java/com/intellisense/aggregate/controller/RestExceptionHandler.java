package com.intellisense.aggregate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller Advice class for exception handling.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, ApiError apiError) {
        return ResponseEntity.status(httpStatus).body(apiError);
    }


    /**
     * handles the generix exception
     *
     * @param ex PaymentException
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex) {
        log.error(ex.toString());
        return buildResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
                new ApiError(ex.getMessage(), ex.toString()));
    }


}
