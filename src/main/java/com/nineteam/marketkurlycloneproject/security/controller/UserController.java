package com.nineteam.marketkurlycloneproject.security.controller;

import com.nineteam.marketkurlycloneproject.security.dto.IdCheckDto;
import com.nineteam.marketkurlycloneproject.security.dto.LoginDto;
import com.nineteam.marketkurlycloneproject.security.dto.ResponseDto;
import com.nineteam.marketkurlycloneproject.security.dto.UserDto;
import com.nineteam.marketkurlycloneproject.security.service.UserService;
import com.nineteam.marketkurlycloneproject.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody UserDto userDto) {
        return userService.signup(userDto);
    }

    @PostMapping("/idcheck")
    public ResponseEntity<ResponseDto> idCheck(@Valid @RequestBody IdCheckDto idCheckDto){
        return userService.idCheck(idCheckDto);
    }

    @GetMapping("/test")
    public String test (@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getUsername();
    }

    @GetMapping("/test2")
    public Optional<String> test2 () {
        return SecurityUtil.getCurrentLoginId();
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

}
