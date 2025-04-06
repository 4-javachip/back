package com.starbucks.back.auth.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseSignInVo {
    private String accessToken;

    @Builder
    public ResponseSignInVo(String accessToken) {
        this.accessToken = accessToken;
    }
}
