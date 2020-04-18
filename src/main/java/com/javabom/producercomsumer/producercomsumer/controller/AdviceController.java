package com.javabom.producercomsumer.producercomsumer.controller;

import com.javabom.producercomsumer.producercomsumer.exception.EventFailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by jyami on 2020/04/19
 */
@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(EventFailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> eventFailException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
    }

}
