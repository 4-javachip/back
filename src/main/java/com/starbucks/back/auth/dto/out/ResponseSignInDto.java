package com.starbucks.back.auth.dto.out;

import com.starbucks.back.auth.vo.out.ResponseSignInVo;
import com.starbucks.back.common.jwt.JwtProvider;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.Authentication;

@Getter
public class ResponseSignInDto {
    private String accessToken;

    @Builder
    public ResponseSignInDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public static ResponseSignInDto of(JwtProvider jwtProvider, Authentication authentication) {
        return ResponseSignInDto.builder()
                .accessToken(jwtProvider.generateAccessToken(authentication))
                .build();
    }

    public ResponseSignInVo toVo() {
        return ResponseSignInVo.builder()
                .accessToken(accessToken)
                .build();
    }
}
