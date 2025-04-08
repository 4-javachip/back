package com.starbucks.back.user.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestMatchPasswordVo {
    @NotBlank
    private String currentPassword;
}
