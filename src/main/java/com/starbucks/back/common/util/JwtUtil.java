package com.starbucks.back.common.util;

import com.starbucks.back.auth.dto.out.ResponseSignInDto;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final RedisUtil<String> redisUtil;
    private final JwtProvider jwtProvider;

    public ResponseSignInDto createLoginToken(String userUuid) {
        try {
            final Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userUuid,
                    null,
                    List.of()
            );

            final String accessToken = jwtProvider.generateAccessToken(authentication);
            final String refreshToken = jwtProvider.generateRefreshToken(authentication);

            redisUtil.set(
                    "Access:" + authentication.getName(),
                    accessToken,
                    30,
                    TimeUnit.MINUTES
            );

            redisUtil.set(
                    "Refresh:" + authentication.getName(),
                    refreshToken,
                    14,
                    TimeUnit.DAYS
            );
            return ResponseSignInDto.of(accessToken, refreshToken);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.LOGIN_FAILED);
        }
    }

}
