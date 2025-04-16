package com.starbucks.back.user.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestAccountRecoveryVo {
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
}
