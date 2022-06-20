package com.nineteam.marketkurlycloneproject.security.validator;

import com.nineteam.marketkurlycloneproject.security.dto.IdCheckDto;
import com.nineteam.marketkurlycloneproject.security.dto.LoginDto;
import com.nineteam.marketkurlycloneproject.security.dto.UserDto;
import com.nineteam.marketkurlycloneproject.security.model.User;
import com.nineteam.marketkurlycloneproject.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserServiceValidator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void idCheckValidation(IdCheckDto idCheckDto) {
        if (userRepository.findOneByLoginId(idCheckDto.getLoginId()).orElse(null) != null)
            throw new IllegalArgumentException("이미 등록된 아이디 입니다.");

        if (!idCheckDto.getLoginId().matches("^[a-zA-Z\\d]*$") || idCheckDto.getLoginId().matches("^[0-9]*$"))
            throw new IllegalArgumentException("영문 혹은 영문과 숫자 조합만 사용 가능합니다.");
    }

    public void signupValidation(UserDto userDto) {
        Pattern passPattern = Pattern.compile("(\\d)\\1\\1");
        Matcher passMatcher = passPattern.matcher(userDto.getPassword());

        if (userRepository.findOneByLoginId(userDto.getLoginId()).orElse(null) != null)
            throw new IllegalArgumentException("이미 등록된 아이디 입니다.");

        if (!userDto.getLoginId().matches("^[a-zA-Z\\d]*$") || userDto.getLoginId().matches("^[0-9]*$"))
            throw new IllegalArgumentException("영문 혹은 영문과 숫자 조합만 사용 가능합니다.");

        if (!Objects.equals(userDto.getPassword(), userDto.getPasswordcheck()))
            throw new IllegalArgumentException("비밀번호 입력내용이 동일하지 않습니다.");

        if (!userDto.getPassword().matches("^.*(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$") && !userDto.getPassword().matches("^.*(?=.*\\d)(?=.*[a-zA-Z]).*$") && !userDto.getPassword().matches("^.*(?=.*\\d)(?=.*[!@#$%^&+=]).*$"))
            throw new IllegalArgumentException("영문/숫자/특수문자(공백제외)만 허용하며, 2개 이상 조합");

        if (passMatcher.find())
            throw new IllegalArgumentException("동일한 숫자 3개 이상 연속 사용 불가");

        if (!userDto.getNickname().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))
            throw new IllegalArgumentException("한글만 사용 가능합니다.");
    }

    public void loginValidation(LoginDto loginDto) {
        User user = userRepository.findOneByLoginId(loginDto.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("비밀번호가 틀립니다.");

    }
}
