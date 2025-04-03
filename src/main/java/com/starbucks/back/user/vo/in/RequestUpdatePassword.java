package com.starbucks.back.user.vo.in;

import lombok.Getter;

@Getter
public class RequestUpdatePassword {
    private String password;
    private String newPassword;
}
