package com.nineteam.marketkurlycloneproject.security.service;

import com.nineteam.marketkurlycloneproject.security.dto.IdCheckDto;
import com.nineteam.marketkurlycloneproject.security.dto.LoginDto;
import com.nineteam.marketkurlycloneproject.security.dto.ResponseDto;
import com.nineteam.marketkurlycloneproject.security.dto.UserDto;
import com.nineteam.marketkurlycloneproject.security.jwt.TokenProvider;
import com.nineteam.marketkurlycloneproject.security.model.User;
import com.nineteam.marketkurlycloneproject.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@AllArgsConstructor
public class UserService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public ResponseEntity<ResponseDto> signup(UserDto userDto) {

        if (userRepository.findOneByLoginId(userDto.getLoginId()).orElse(null) != null)
            throw new IllegalArgumentException("이미 등록된 아이디 입니다.");

        if (!Objects.equals(userDto.getPassword(), userDto.getPasswordcheck()))
            throw new IllegalArgumentException("비밀번호 입력내용이 동일하지 않습니다.");

        if (!userDto.getPassword().matches("^.*(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$") && !userDto.getPassword().matches("^.*(?=.*\\d)(?=.*[a-zA-Z]).*$") && !userDto.getPassword().matches("^.*(?=.*\\d)(?=.*[!@#$%^&+=]).*$"))
            throw new IllegalArgumentException("영문/숫자/특수문자(공백제외)만 허용하며, 2개 이상 조합");

        Pattern passPattern = Pattern.compile("(\\d)\\1\\1");
        Matcher passMatcher = passPattern.matcher(userDto.getPassword());

        if (passMatcher.find())
            throw new IllegalArgumentException("동일한 숫자 3개 이상 연속 사용 불가");

        if (!userDto.getNickName().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))
            throw new IllegalArgumentException("한글만 사용 가능합니다.");

        User user = User.builder()
                .loginId(userDto.getLoginId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickName(userDto.getNickName())
                .address(userDto.getAddress())
                .role("ROLE_USER")
                .build();
        userRepository.save(user);

        return new ResponseEntity<>(new ResponseDto(true, "회원가입 성공"), HttpStatus.OK);

    }

    public ResponseEntity<ResponseDto> idCheck(IdCheckDto idCheckDto) {

        if (userRepository.findOneByLoginId(idCheckDto.getLoginId()).orElse(null) != null)
            throw new IllegalArgumentException("이미 등록된 아이디 입니다.");
        if (!idCheckDto.getLoginId().matches("^[a-zA-Z\\d]*$") || idCheckDto.getLoginId().matches("^[0-9]*$"))
            throw new IllegalArgumentException("영문 혹은 영문과 숫자 조합만 사용 가능합니다.");
        return new ResponseEntity<>(new ResponseDto(true, "사용이 가능합니다."), HttpStatus.OK);

    }


    @Transactional
    public ResponseEntity<ResponseDto> login(LoginDto loginDto) {

        User user = userRepository.findOneByLoginId(loginDto.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("비밀번호가 틀립니다.");

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getLoginId(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt);

        return new ResponseEntity<>(new ResponseDto(true, "로그인 성공"), httpHeaders, HttpStatus.OK);
    }


}
