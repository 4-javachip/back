package com.starbucks.back.oauth.application;


import com.starbucks.back.auth.dto.out.ResponseSignInDto;
import com.starbucks.back.oauth.dto.in.RequestOauthUserInfoDto;
import com.starbucks.back.oauth.dto.in.RequestOauthSignUpDto;
import com.starbucks.back.oauth.dto.out.ResponseOauthUserInfoDto;

public interface OauthService {
    ResponseOauthUserInfoDto getOauthUserInfo(RequestOauthUserInfoDto requestOauthUserInfoDto) throws Exception;
    ResponseSignInDto oauthSignIn(RequestOauthUserInfoDto requestOauthUserInfoDto) throws Exception;
    void oauthSignUp(RequestOauthSignUpDto requestOauthSignUpDto);
    Boolean existsOauth(String userUuid);
    void withdrawalPendingOauth(String userUuid);
    void deleteOauth(String userUuid);
}
