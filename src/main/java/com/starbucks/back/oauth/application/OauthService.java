package com.starbucks.back.oauth.application;


import com.starbucks.back.auth.dto.out.ResponseSignInDto;
import com.starbucks.back.oauth.dto.in.RequestOauthUserInfoDto;
import com.starbucks.back.oauth.dto.in.RequestOauthSignUpDto;
import com.starbucks.back.oauth.dto.out.ResponseOauthUserInfoDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface OauthService {
    ResponseOauthUserInfoDto getOauthUserInfo(RequestOauthUserInfoDto requestOauthUserInfoDto) throws Exception;
    ResponseSignInDto oauthSignIn(RequestOauthUserInfoDto requestOauthUserInfoDto, HttpServletResponse httpServletResponse) throws Exception;
    void oauthSignUp(RequestOauthSignUpDto requestOauthSignUpDto, String oauthCookieValue) throws Exception;
    Boolean existsOauth(String userUuid);
    void withdrawalPendingOauth(String userUuid);
    void deleteOauth(String userUuid);
}
