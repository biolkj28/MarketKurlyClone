package com.nineteam.marketkurlycloneproject.security.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull(message = "아이디를 입력해주세요")
    @Size(min = 6, max = 16, message = "6자 이상 16자 이하로 입력해주세요")
    private String loginId;

    @NotNull(message = "비밀번호를 입력해주세요")
    @Size(min = 10, max = 16, message = "10자 이상 16자 이하로 입력해주세요")
    private String password;
}
