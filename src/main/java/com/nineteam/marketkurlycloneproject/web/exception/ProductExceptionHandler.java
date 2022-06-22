package com.nineteam.marketkurlycloneproject.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> notExistProduct(NullPointerException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }
}
