package com.starbucks.back.oauth.dto.in;

import com.starbucks.back.oauth.domain.Oauth;
import com.starbucks.back.oauth.domain.enums.SocialProvider;
import com.starbucks.back.oauth.vo.in.RequestOauthUserInfoVo;
import com.starbucks.back.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestOauthUserInfoDto {
    private SocialProvider provider;
    private String accessToken;

    @Builder
    public RequestOauthUserInfoDto(SocialProvider provider, String accessToken) {
        this.provider = provider;
        this.accessToken = accessToken;
    }

    public static RequestOauthUserInfoDto from(RequestOauthUserInfoVo requestOauthUserInfoVo) {
        return RequestOauthUserInfoDto.builder()
                .provider(SocialProvider.valueOf(requestOauthUserInfoVo.getProvider().toUpperCase()))
                .accessToken(requestOauthUserInfoVo.getAccessToken())
                .build();
    }

}
