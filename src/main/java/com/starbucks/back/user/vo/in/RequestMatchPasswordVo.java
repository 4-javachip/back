package com.starbucks.back.user.vo.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RequestMatchPasswordVo {
    @NotBlank(message = "현재 비밀번호를 입력해주세요.")
    private String currentPassword;

    @NotBlank(message = "비밀번호 확인을 위한 목적을 입력해주세요.")
    private String purpose;
}
