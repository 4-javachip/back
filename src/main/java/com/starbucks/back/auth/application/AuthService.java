package com.starbucks.back.auth.application;

import com.starbucks.back.auth.dto.in.RequestSignInDto;
import com.starbucks.back.auth.dto.in.RequestSignUpDto;
import com.starbucks.back.auth.dto.out.ResponseSignInDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    void signUp(RequestSignUpDto requestSignUpDto);
    ResponseSignInDto signIn(RequestSignInDto requestSignInDto, HttpServletResponse httpServletResponse);
    ResponseSignInDto reissueToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
    void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
    boolean existsEmail(String email);
    boolean existsNickname(String nickname);
}
