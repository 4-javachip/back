package com.starbucks.back.oauth.vo.in;

import com.starbucks.back.oauth.domain.enums.SocialProvider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RequestOauthUserInfoVo {
    @NotNull(message = "소셜 플랫폼을 입력해주세요.")
    private String provider;

    @NotBlank(message = "엑세스 토큰을 입력해주세요.")
    private String accessToken;
}
