package com.nineteam.marketkurlycloneproject.security.service;

import com.nineteam.marketkurlycloneproject.security.dto.*;
import com.nineteam.marketkurlycloneproject.security.jwt.TokenProvider;
import com.nineteam.marketkurlycloneproject.security.model.User;
import com.nineteam.marketkurlycloneproject.security.repository.UserRepository;
import com.nineteam.marketkurlycloneproject.security.validator.UserServiceValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceValidator userServiceValidator;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public ResponseEntity<ResponseDto> idCheck(IdCheckDto idCheckDto) {
        userServiceValidator.idCheckValidation(idCheckDto);
        return new ResponseEntity<>(new ResponseDto(true, "사용이 가능합니다."), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<ResponseDto> signup(UserDto userDto) {
        userServiceValidator.signupValidation(userDto);

        User user = User.builder()
                .loginId(userDto.getLoginId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickName(userDto.getNickname())
                .address(userDto.getAddress())
                .role("ROLE_USER")
                .build();
        userRepository.save(user);

        return new ResponseEntity<>(new ResponseDto(true, "회원가입 성공"), HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<LoginResponseDto> login(LoginDto loginDto) {
        userServiceValidator.loginValidation(loginDto);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getLoginId(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Authorization", "Bearer " + jwt);

        return new ResponseEntity<>(new LoginResponseDto(true, "로그인 성공", jwt), HttpStatus.OK);
    }


}
