package com.starbucks.back.user.vo.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RequestVerificationEmailVo {
    @NotBlank
    private String email;

    @NotBlank
    @Size(min=6, max=6, message = "인증번호는 6글자 입니다.")
    private String verificationCode;

    @NotBlank
    private String purpose;
}
