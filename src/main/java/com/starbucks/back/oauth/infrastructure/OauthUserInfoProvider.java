package com.starbucks.back.oauth.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.oauth.domain.enums.SocialProvider;
import com.starbucks.back.oauth.dto.out.ResponseOauthUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@RequiredArgsConstructor
public class OauthUserInfoProvider {

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public ResponseOauthUserInfoDto getGoogleUser(String token) throws Exception {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://www.googleapis.com/oauth2/v2/userinfo"))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 401) {
            throw new BaseException(BaseResponseStatus.INVALID_GOOGLE_TOKEN);
        }

        final JsonNode body = objectMapper.readTree(response.body());

        return ResponseOauthUserInfoDto.builder()
                .email(body.get("email").asText())
                .name(body.get("name").asText())
                .providerUserId(body.get("id").asText())
                .provider(SocialProvider.GOOGLE)
                .build();
    }

    public ResponseOauthUserInfoDto getKakaoUser(String token) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://kapi.kakao.com/v2/user/me"))
                .header("Authorization", "Bearer " + token)
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();

        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 401) {
            throw new BaseException(BaseResponseStatus.INVALID_KAKAO_TOKEN);
        }

        final JsonNode body = objectMapper.readTree(response.body());

        final JsonNode kakaoAccount = body.get("kakao_account");
        final JsonNode profile = kakaoAccount.get("profile");

        return ResponseOauthUserInfoDto.builder()
                .email(kakaoAccount.get("email").asText())
                .name(profile.has("nickname") ? profile.get("nickname").asText() : "KakaoUser")
                .provider(SocialProvider.KAKAO)
                .providerUserId(body.get("id").asText())
                .build();
    }
}
