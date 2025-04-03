package com.starbucks.back.user.vo.in;

import com.starbucks.back.user.domain.enums.UserGender;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RequestSignUpVo {
    @NotBlank
    @Size(min=10, max=30, message = "이메일은 10자 이상 30자 이하로 입력해주세요.")
    @Email(message = "올바른 이메일 형식을 입력해주세요.")
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{10,20}$",
            message = "비밀번호는 영문자, 숫자, 특수문자를 포함하여 10~20자여야 합니다."
    )
    private String password;

    @NotBlank
    @Size(min=2, max=20, message = "이름은 2자 이상 20자 이하로 입력해주세요.")
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
