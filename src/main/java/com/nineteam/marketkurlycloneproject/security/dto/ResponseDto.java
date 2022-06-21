package com.nineteam.marketkurlycloneproject.security.dto;

import lombok.Getter;

@Getter
public class ResponseDto {
    private final boolean ok;
    private final String message;

    public ResponseDto(boolean ok, String message){
        this.ok = ok;
        this.message = message;
    }
}
