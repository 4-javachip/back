package com.starbucks.back.user.vo.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RequestSendEmailCodeVo {
    @NotBlank
    @Size(min=10, max=30, message = "이메일은 10자 이상 30자 이하로 입력해주세요.")
    @Email(message = "올바른 이메일 형식을 입력해주세요.")
    private String email;

//    @NotBlank
//    @Size(min=6, max=6, message = "인증번호는 6글자 합니다.")
//    private String verificationCode;
}
