package com.javabom.producercomsumer.producercomsumer.service.exception;

import org.springframework.http.HttpStatus;

public class PayFailException extends RuntimeException {
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    private String message;

    public PayFailException(String message) {
        this.message = message;
    }
}
