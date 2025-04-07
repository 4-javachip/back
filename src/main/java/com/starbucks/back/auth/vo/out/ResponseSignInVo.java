package com.starbucks.back.auth.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseSignInVo {
    private String accessToken;
    private String refreshToken;

    @Builder
    public ResponseSignInVo(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
