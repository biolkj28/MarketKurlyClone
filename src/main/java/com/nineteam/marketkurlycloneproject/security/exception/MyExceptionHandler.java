package com.nineteam.marketkurlycloneproject.security.exception;

import com.nineteam.marketkurlycloneproject.security.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * @valid 유효성체크에 통과하지 못하면  MethodArgumentNotValidException 이 발생한다.
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> methodValidException(MethodArgumentNotValidException e) {
        ResponseDto errorResponse = new ResponseDto(false, e.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto> illegalArgumentException(IllegalArgumentException e) {
        ResponseDto errorResponse = new ResponseDto(false, e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}