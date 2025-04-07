package com.starbucks.back.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtProvider {

    private final Environment env;

    /**
     * TokenProvider
     * 1. 토큰에서 uuid 가져오기
     * 2. Claim's 원하는 claim 값 추출 ( - JWT version 업데이트로 사용하지 않음)
     * 3. 토큰에서 모든 claims 추출 ( - JWT version 업데이트로 사용하지 않음)
     * 4. 액세스 토큰 생성
     * 5. refresh 토큰 생성
     */

    /**
     * 1. 토큰에서 uuid 가져오기
     * @param token
     * @return jwt토큰에서 추출한 사용자 UUID 반환
     * @throws IllegalArgumentException
     */
    public String validateAndGetUserUuid(String token) throws IllegalArgumentException {
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        try {
            return claims.get("uuid", String.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("토큰에서 UUID를 추출할 수 없습니다", e);
        }
    }

    /**
     * 2. Claims에서 원하는 claim 값 추출
     * @param token
     * @param claimsResolver jwt토큰에서 추출한 정보를 어떻게 처리할지 결정하는 함수
     * @return jwt토큰에서 모든 클레임(클레임은 토큰에 담긴 정보 의미 ) 추출한 다음 claimsResolver함수를 처리해 결과 반환
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 3. 토큰에서 모든 claims 추출
     * @param token
     * @return jwt토큰에서 모든 클레임 추출
     */
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 4. 액세스 토큰 생성
     * @param authentication
     * @return 액세스 토큰
     */
    public String generateAccessToken(Authentication authentication) {
        String userUuid = (String) authentication.getPrincipal();
        Claims claims = Jwts.claims().subject(authentication.getName()).build();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + env.getProperty("JWT.token.access-expire-time", Long.class));

        return Jwts.builder()
                .signWith(getSignKey())
                .claim("uuid", userUuid)
                .issuedAt(now)
                .expiration(expiration)
                .compact();
    }

    /**
     * 5. refresh 토큰 생성
     * @param authentication
     * @return refresh 토큰
     */
    public String generateRefreshToken(Authentication authentication) {
        String userUuid = (String) authentication.getPrincipal();
        Claims claims = Jwts.claims().subject(authentication.getName()).build();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + env.getProperty("JWT.token.refresh-expire-time", Long.class));

        return Jwts.builder()
                .signWith(getSignKey())
                .claim("uuid", userUuid)
                .issuedAt(now)
                .expiration(expiration)
                .compact();
    }

    /**
     * 6. refreshToken을 HttpOnly 쿠키로 설정
     * @param httpServletResponse
     * @param refreshToken
     */
    public void setRefreshTokenToCookie(HttpServletResponse httpServletResponse, String refreshToken) {
        ResponseCookie cookie = ResponseCookie.from("refresh-token", refreshToken)
                .httpOnly(true)
                .secure(false) // 개발 환경에서만 사용, 배포 시 true로 변경
                .sameSite("None")
                .path("/")
                .maxAge(Duration.ofDays(14))
                .build();

        httpServletResponse.addHeader("Set-Cookie", cookie.toString());
    }

    /**
     * 7. 쿠키에서 refreshToken 추출
     * @param httpServletRequest
     */
    public Optional<String> extractRefreshTokenFromCookie(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getCookies() == null) return Optional.empty();
        return Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> "refresh-token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }

    /**
     * 8. 쿠키 삭제
     * @return
     */
    public void clearRefreshTokenCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("refresh-token", "")
                .httpOnly(true)
                .secure(false) // 개발 환경에서만 사용, 배포 시 true로 변경
                .path("/")
                .maxAge(0)
                .sameSite("None")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }



    public Key getSignKey() {
        return Keys.hmacShaKeyFor(env.getProperty("JWT.secret-key").getBytes());
    }

}
