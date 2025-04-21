package com.starbucks.back.oauth.application;

import com.starbucks.back.auth.application.AuthService;
import com.starbucks.back.auth.dto.out.ResponseSignInDto;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.util.JwtUtil;
import com.starbucks.back.common.util.RedisUtil;
import com.starbucks.back.oauth.domain.Oauth;
import com.starbucks.back.oauth.domain.enums.OauthState;
import com.starbucks.back.oauth.dto.in.RequestOauthSignUpDto;
import com.starbucks.back.oauth.dto.in.RequestOauthUserInfoDto;
import com.starbucks.back.oauth.dto.out.ResponseOauthUserInfoDto;
import com.starbucks.back.oauth.infrastructure.OauthRepository;
import com.starbucks.back.oauth.infrastructure.OauthUserInfoProvider;
import com.starbucks.back.user.domain.User;
import com.starbucks.back.user.infrastructure.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{

    private final OauthRepository oauthRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final RedisUtil<String> redisUtil;
    private final OauthUserInfoProvider oauthUserInfoProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseOauthUserInfoDto getOauthUserInfo(RequestOauthUserInfoDto requestOauthUserInfoDto) throws Exception {
            switch (requestOauthUserInfoDto.getProvider()) {
                case GOOGLE -> {
                    return oauthUserInfoProvider.getGoogleUser(requestOauthUserInfoDto.getAccessToken());
                }
                case KAKAO -> {
                    return oauthUserInfoProvider.getKakaoUser(requestOauthUserInfoDto.getAccessToken());
                }
                default -> throw new BaseException(BaseResponseStatus.NOT_SUPPORTED_OAUTH);
            }
    }

    @Transactional
    @Override
    public ResponseSignInDto oauthSignIn (
            RequestOauthUserInfoDto requestOauthUserInfoDto,
            HttpServletResponse httpServletResponse
    ) throws Exception {
        ResponseOauthUserInfoDto dto = getOauthUserInfo(requestOauthUserInfoDto);

        final Oauth oauth = oauthRepository.findByProviderAndProviderUserId(
                dto.getProvider(),
                dto.getProviderUserId()
        ).orElse(null);

        //소셜 정보가 없음 -> 해당 이메일로 가입된 유저가 있는지 확인, 없다면 가입 요청 에러, 있다면 소셜 정보 저장
        if (oauth == null) {
            final User user = userRepository.findByEmail(dto.getEmail()).orElse(null);
            if (user == null) {
                // 가입된 유저가 없으면 회원가입 진행을 위한 랜덤 토큰 생성
                final String token = UUID.randomUUID().toString();
                final String redisKey = "oauth_email:" + token;
                // Redis에 이메일 저장 (만료시간 20분)
                redisUtil.set(redisKey, dto.getEmail(), 20, TimeUnit.MINUTES);

                final String cookie = "oauth_cookie=" + token
                        + "; Max-Age=" + (20 * 60)
                        + "; HttpOnly"
                        + "; Secure"        // 로컬이면 제거
                        + "; SameSite=None" // 크로스 도메인 대응
                        + "; Domain=.starbucks-store.shop" // 운영환경에서만 설정
                        + "; Path=/";
                httpServletResponse.setHeader("Set-Cookie", cookie);

                throw new BaseException(BaseResponseStatus.NO_OAUTH_USER);
            }else {
                oauthRepository.save(
                        oauth.builder()
                        .userUuid(user.getUserUuid())
                        .provider(dto.getProvider())
                        .providerUserId(dto.getProviderUserId())
                        .state(OauthState.ACTIVE)
                        .build());
                return jwtUtil.createLoginToken(user.getUserUuid());
            }
        }

        //소셜 정보가 있음 -> state를 확인하여 탈퇴 예정 상태이면 에러 처리
        if (oauth.getState() == OauthState.WITHDRAWAL_PENDING) {
            throw new BaseException(BaseResponseStatus.WITHDRAWAL_PENDING);
        }

        return jwtUtil.createLoginToken(oauth.getUserUuid());
    }

    @Transactional
    @Override
    public void oauthSignUp(
            RequestOauthSignUpDto requestOauthSignUpDto,
            String oauthCookieValue
    ) {
        verifyOauthToken(requestOauthSignUpDto, oauthCookieValue);

        if (authService.existsNickname(requestOauthSignUpDto.getNickname())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_NICKNAME);
        } else if (authService.existsPhoneNumber(requestOauthSignUpDto.getPhoneNumber())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_PHONE_NUMBER);
        }

        authService.oauthSignUp(requestOauthSignUpDto.toEntity(passwordEncoder));
    }

    public void verifyOauthToken(
            RequestOauthSignUpDto requestOauthSignUpDto
            , String oauthCookieValue
    ){
        // oauthCookieValue 값 검증
        if (oauthCookieValue == null || oauthCookieValue.trim().isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_COOKIE);
        }

        final String redisKey = "oauth_email:" + oauthCookieValue;
        final String storedEmail = redisUtil.get(redisKey);
        if (storedEmail == null) {
            throw new BaseException(BaseResponseStatus.INVALID_OAUTH_TOKEN);
        }

        // Redis에 저장된 이메일로 회원가입 DTO 업데이트
        requestOauthSignUpDto.updateEmail(storedEmail);

        // 재사용 방지를 위해 Redis에 해당 키 삭제
        redisUtil.delete(redisKey);
    }

    @Override
    public Boolean existsOauth(String userUuid) {
        return oauthRepository.existsByUserUuid(userUuid);
    }

    @Transactional
    @Override
    public void withdrawalPendingOauth (String userUuid) {
        final List<Oauth> oauthList = oauthRepository.findByUserUuid(userUuid);

        if (!oauthList.isEmpty()) {
            oauthRepository.saveAll(
                    oauthList.stream()
                            .map(oauth -> Oauth.builder()
                                    .id(oauth.getId())
                                    .userUuid(oauth.getUserUuid())
                                    .provider(oauth.getProvider())
                                    .providerUserId(oauth.getProviderUserId())
                                    .state(OauthState.WITHDRAWAL_PENDING)
                                    .build())
                            .toList()
            );
        }
    }

    @Override
    public void deleteOauth(String userUuid) {

    }
}
