package com.starbucks.back.oauth.vo.in;

import com.starbucks.back.user.domain.enums.UserGender;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RequestOauthSignUpVo {
    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{10,20}$",
            message = "비밀번호는 영문자, 숫자, 특수문자를 포함하여 10~20자여야 합니다."
    )
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min=2, max=10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    private String nickname;

    @NotBlank
    @Pattern(
            regexp = "^010-\\d{4}-\\d{4}$",
            message = "전화번호는 010-xxxx-xxxx 형식이어야 합니다."
    )
    private String phoneNumber;

    @NotNull
    private LocalDate birthdate;

    @NotNull
    private UserGender gender;
}
