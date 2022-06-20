package com.nineteam.marketkurlycloneproject.security.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "아이디를 입력해주세요")
    @Size(min = 6, max = 16, message = "아이디를 6자 이상 16자 이하로 입력해주세요")
    private String loginId;

    @NotNull(message = "비밀번호를 입력해주세요")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 10, max = 16, message = "비밀번호를 10자 이상 16자 이하로 입력해주세요")
    private String password;

    @NotNull(message = "비밀번호를 한번 더 입력해주세요")
    @Size(min = 10, max = 16, message = "비밀번호 확인을 10자 이상 16자 이하로 입력해주세요")
    private String passwordcheck;

    @NotNull(message = "닉네임을 입력해주세요")
    @Size(min = 2, max = 8, message = "닉네임을 2자 이상 8자 이하로 입력해주세요")
    private String nickname;

//    @NotNull(message = "주소를 입력해주세요")
//    private String address;

}
