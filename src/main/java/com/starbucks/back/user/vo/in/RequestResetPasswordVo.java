package com.starbucks.back.user.vo.in;

import lombok.Getter;

@Getter
public class RequestResetPasswordVo {
    private String email;
    private String newPassword;
    private String confirmPassword;
}
