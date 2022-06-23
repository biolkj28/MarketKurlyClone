package com.nineteam.marketkurlycloneproject.security.controller;

import com.nineteam.marketkurlycloneproject.security.dto.*;
import com.nineteam.marketkurlycloneproject.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/idcheck")
    public ResponseEntity<ResponseDto> idCheck(@Valid @RequestBody IdCheckDto idCheckDto){
        return userService.idCheck(idCheckDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody UserDto userDto) {
        return userService.signup(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }



}
