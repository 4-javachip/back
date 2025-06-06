package com.starbucks.back.auth.dto.out;

import com.starbucks.back.auth.vo.out.ResponseSignInVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseSignInDto {
    private String accessToken;
    private String refreshToken;

    @Builder
    public ResponseSignInDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static ResponseSignInDto of(String accessToken, String refreshToken) {
        return ResponseSignInDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public ResponseSignInVo toVo() {
        return ResponseSignInVo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
