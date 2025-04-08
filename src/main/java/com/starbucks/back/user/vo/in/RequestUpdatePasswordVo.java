package com.starbucks.back.user.vo.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class RequestUpdatePasswordVo {
    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{10,20}$",
            message = "비밀번호는 영문자, 숫자, 특수문자를 포함하여 10~20자여야 합니다."
    )
    private String newPassword;
    @NotBlank
    private String confirmPassword;
}
