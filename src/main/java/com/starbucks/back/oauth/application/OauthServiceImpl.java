package com.starbucks.back.oauth.application;

import com.starbucks.back.auth.application.AuthService;
import com.starbucks.back.auth.dto.out.ResponseSignInDto;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.util.JwtUtil;
import com.starbucks.back.oauth.domain.Oauth;
import com.starbucks.back.oauth.dto.in.RequestOauthSignUpDto;
import com.starbucks.back.oauth.dto.in.RequestOauthUserInfoDto;
import com.starbucks.back.oauth.dto.out.ResponseOauthUserInfoDto;
import com.starbucks.back.oauth.infrastructure.OauthRepository;
import com.starbucks.back.oauth.infrastructure.OauthUserInfoProvider;
import com.starbucks.back.user.application.UserService;
import com.starbucks.back.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@Slf4j
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{

    private final OauthRepository oauthRepository;
    private final UserService userService;
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final OauthUserInfoProvider oauthUserInfoProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseOauthUserInfoDto getOauthUserInfo(RequestOauthUserInfoDto requestOauthUserInfoDto) throws Exception {
            switch (requestOauthUserInfoDto.getProvider()) {
                case GOOGLE -> {
                    return oauthUserInfoProvider.getGoogleUser(requestOauthUserInfoDto.getAccessToken());
                }
                case NAVER -> {
                    return oauthUserInfoProvider.getNaverUser(requestOauthUserInfoDto.getAccessToken());
                }
                case KAKAO -> {
                    return oauthUserInfoProvider.getKakaoUser(requestOauthUserInfoDto.getAccessToken());
                }
                default -> throw new IllegalArgumentException("지원하지 않는 소셜 로그인입니다.");
            }
    }

    @Override
    public ResponseSignInDto oauthSignIn (RequestOauthUserInfoDto requestOauthUserInfoDto) throws Exception {
        ResponseOauthUserInfoDto dto = getOauthUserInfo(requestOauthUserInfoDto);

        final Oauth oauth = oauthRepository.findByProviderAndProviderUserId(
                dto.getProvider(),
                dto.getProviderUserId()
        ).orElse(null);

        //소셜 정보가 없음 -> 해당 이메일로 가입된 유저가 있는지 확인, 없다면 가입 요청 에러, 있다면 소셜 정보 저장
        if (oauth == null ) {
            final User user = userService.loadUserByEmail(dto.getEmail());
            if (user == null) {
                throw new BaseException(BaseResponseStatus.NO_OAUTH_USER);
            }else {
                oauthRepository.save(
                        oauth.builder()
                        .userUuid(user.getUserUuid())
                        .provider(dto.getProvider())
                        .providerUserId(dto.getProviderUserId())
                        .build());
                return jwtUtil.createLoginToken(user.getUserUuid());
            }
        }

        return jwtUtil.createLoginToken(oauth.getUserUuid());
    }

    @Override
    public void oauthSignUp(RequestOauthSignUpDto requestOauthSignUpDto) {
        if(authService.existsEmail(requestOauthSignUpDto.getEmail())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_EMAIL);
        } else if (authService.existsNickname(requestOauthSignUpDto.getNickname())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_NICKNAME);
        } else if (authService.existsPhoneNumber(requestOauthSignUpDto.getPhoneNumber())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_PHONE_NUMBER);
        }

        authService.addUser(requestOauthSignUpDto.toEntity(passwordEncoder));
    }


}
