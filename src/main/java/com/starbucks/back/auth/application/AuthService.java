package com.starbucks.back.auth.application;

import com.starbucks.back.auth.dto.in.RequestSignInDto;
import com.starbucks.back.auth.dto.in.RequestSignUpDto;
import com.starbucks.back.auth.dto.out.ResponseSignInDto;
import com.starbucks.back.user.domain.User;
import com.starbucks.back.auth.dto.in.RequestGetUserNicknameDto;
import com.starbucks.back.auth.dto.out.ResponseGetUserNicknameDto;

public interface AuthService {
    void signUp(RequestSignUpDto requestSignUpDto);
    ResponseSignInDto signIn(RequestSignInDto requestSignInDto);
    ResponseSignInDto reissueAllToken(String refreshToken);
    void logout(String refreshToken);
    boolean existsEmail(String email);
    boolean existsNickname(String nickname);
    boolean existsPhoneNumber(String phoneNumber);
    void oauthSignUp(User user);
    ResponseGetUserNicknameDto getUserNickname(RequestGetUserNicknameDto requestGetUserNicknameDto);
    ResponseSignInDto qrSignIn(RequestSignInDto requestSignInDto);
}
