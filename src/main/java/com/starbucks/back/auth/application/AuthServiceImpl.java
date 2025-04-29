package com.starbucks.back.auth.application;

import com.starbucks.back.auth.dto.in.RequestSignInDto;
import com.starbucks.back.auth.dto.in.RequestSignUpDto;
import com.starbucks.back.auth.dto.out.ResponseSignInDto;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.jwt.JwtProvider;
import com.starbucks.back.common.util.JwtUtil;
import com.starbucks.back.common.util.RedisUtil;
import com.starbucks.back.user.domain.User;
import com.starbucks.back.user.domain.enums.UserState;
import com.starbucks.back.auth.dto.in.RequestGetUserNicknameDto;
import com.starbucks.back.auth.dto.out.ResponseGetUserNicknameDto;
import com.starbucks.back.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisUtil<String> redisUtil;
    private final JwtUtil jwtUtil;

    @Transactional
    @Override
    public void signUp(RequestSignUpDto requestSignUpDto) {
        if(userRepository.existsByEmail(requestSignUpDto.getEmail())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_EMAIL);
        } else if (userRepository.existsByNickname(requestSignUpDto.getNickname())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_NICKNAME);
        } else if (userRepository.existsByPhoneNumber(requestSignUpDto.getPhoneNumber())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_PHONE_NUMBER);
        }

        if (!"true".equals(redisUtil.get("SignUp:Verified:" + requestSignUpDto.getEmail()))) {
            throw new BaseException(BaseResponseStatus.SIGN_UP_NOT_VERIFIED);
        }
        userRepository.save(requestSignUpDto.toEntity(passwordEncoder));
    }

    @Transactional
    @Override
    public ResponseSignInDto signIn(
            RequestSignInDto requestSignInDto
    ) {
        final User user = userRepository.findByEmail(requestSignInDto.getEmail())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.INVALID_LOGIN));

        // 비밀번호 검증
        if (!passwordEncoder.matches(requestSignInDto.getPassword(), user.getPassword())) {
            throw new BaseException(BaseResponseStatus.INVALID_LOGIN);
        } else if (user.getState() == UserState.WITHDRAWAL_PENDING) {
            throw new BaseException(BaseResponseStatus.WITHDRAWAL_PENDING);
        }


        return jwtUtil.createLoginToken(user.getUserUuid());
    }

    @Transactional
    @Override
    public ResponseSignInDto reissueAllToken(
            String refreshToken
    ) {
        final String userUuid = jwtProvider.validateAndGetUserUuid(refreshToken);
        final String redisAccessTokenKey = "Access:" + userUuid;
        final String redisRefreshTokenKey = "Refresh:" + userUuid;

        if (redisUtil.get(redisRefreshTokenKey) == null || !redisUtil.get(redisRefreshTokenKey).equals(refreshToken)) {
            throw new BaseException(BaseResponseStatus.INVALID_REFRESH_TOKEN);
        }

        return jwtUtil.createLoginToken(userUuid);
   }

   @Transactional
   @Override
    public void logout(String refreshToken) {
           try {
               redisUtil.delete("Access:" + jwtProvider.validateAndGetUserUuid(refreshToken));
               redisUtil.delete("Refresh:" + jwtProvider.validateAndGetUserUuid(refreshToken));
           } catch (Exception e) {}
   }

    @Override
    public boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean existsPhoneNumber(String phoneNumber) { return userRepository.existsByPhoneNumber(phoneNumber); }

    @Override
    public void oauthSignUp(User user) {userRepository.save(user);}

    @Override
    public ResponseGetUserNicknameDto getUserNickname(RequestGetUserNicknameDto requestGetUserNicknameDto) {
        return ResponseGetUserNicknameDto.from(
                userRepository.findByUserUuid(requestGetUserNicknameDto.getUserUuid())
                        .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER))
                        .getNickname()
        );
    }

    @Transactional
    @Override
    public ResponseSignInDto qrSignIn(
            RequestSignInDto requestSignInDto
    ) {
        final User user = userRepository.findByEmail(requestSignInDto.getEmail())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.INVALID_LOGIN));

        // 비밀번호 검증
        if (!passwordEncoder.matches(requestSignInDto.getPassword(), user.getPassword())) {
            throw new BaseException(BaseResponseStatus.INVALID_LOGIN);
        } else if (user.getState() == UserState.WITHDRAWAL_PENDING) {
            throw new BaseException(BaseResponseStatus.WITHDRAWAL_PENDING);
        }

        final String accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwidXVpZCI6IjVlNGM1NjFlLTU1YTgtNGE1MS1hNzg3LTQ2MGYwYTcyMmIwYiIsImlhdCI6MTc0NTg5Mzk0NiwiZXhwIjoxNzQ1OTgwMzQ2fQ.i1gQLYYmJ3GxNp8GJRfdcsC2aSA_Hq0QwTz2X6GLGjMKeYfagqr0TUrxJkpIyRNRXt3JY-qyOD9h7YCi-uJEVA";
        final String refreshToken = "eyJhbGciOiJIUzUxMiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsInV1aWQiOiI1ZTRjNTYxZS01NWE4LTRhNTEtYTc4Ny00NjBmMGE3MjJiMGIiLCJpYXQiOjE3NDU4OTM5NDYsImV4cCI6MTc0NzEwMzU0Nn0._k_1m6U5mCAbY-MTsVEfl69xHKHJLI4gv_-HArSe-A2Qt3hqT02-faRlKrMsWdOemOcCKpUvXgN7eGHRGKm-UA";

        redisUtil.set(
                "Access:" + user.getUserUuid(),
                accessToken,
                14,
                TimeUnit.DAYS
        );

        redisUtil.set(
                "Refresh:" + user.getUserUuid(),
                refreshToken,
                14,
                TimeUnit.DAYS
        );
            return ResponseSignInDto.of(accessToken, refreshToken);
        }
}

