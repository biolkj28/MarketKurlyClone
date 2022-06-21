package com.nineteam.marketkurlycloneproject.security.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final boolean ok;
    private final String message;
    private final String jwt;


    public LoginResponseDto(boolean ok, String message, String jwt){
        this.ok = ok;
        this.message = message;
        this.jwt = jwt;
    }
}
