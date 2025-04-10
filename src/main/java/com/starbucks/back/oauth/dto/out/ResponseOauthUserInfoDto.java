package com.starbucks.back.oauth.dto.out;

import com.starbucks.back.oauth.domain.enums.SocialProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseOauthUserInfoDto {
    private String email;
    private String name;
    private SocialProvider provider;
    private String providerUserId;

    @Builder
    public ResponseOauthUserInfoDto(String email, String name, SocialProvider provider, String providerUserId) {
        this.email = email;
        this.name = name;
        this.provider = provider;
        this.providerUserId = providerUserId;
    }

    public static ResponseOauthUserInfoDto from(String email, String name, SocialProvider provider, String providerUserId) {
        return ResponseOauthUserInfoDto.builder()
                .email(email)
                .name(name)
                .provider(provider)
                .providerUserId(providerUserId)
                .build();
    }
}
