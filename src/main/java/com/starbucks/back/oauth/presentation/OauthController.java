package com.starbucks.back.oauth.presentation;

import com.starbucks.back.auth.vo.out.ResponseSignInVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.oauth.application.OauthService;
import com.starbucks.back.oauth.dto.in.RequestOauthSignUpDto;
import com.starbucks.back.oauth.dto.in.RequestOauthUserInfoDto;
import com.starbucks.back.oauth.dto.out.ResponseOauthUserInfoDto;
import com.starbucks.back.oauth.vo.in.RequestOauthSignUpVo;
import com.starbucks.back.oauth.vo.in.RequestOauthUserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/oauth")
@RequiredArgsConstructor
public class OauthController {

    private final OauthService oauthService;

    @Operation(summary = "Get Oauth User Info API", description = "Oauth 사용자 정보 조회", tags = {"Oauth-service"})
    @PostMapping("/user-info")
    public BaseResponseEntity<ResponseOauthUserInfoDto> getOauthUserInfo(
            @RequestBody @Valid RequestOauthUserInfoVo requestOauthUserInfoVo
            ) throws Exception {
        return new BaseResponseEntity<>(
                oauthService.getOauthUserInfo(
                        RequestOauthUserInfoDto.from(requestOauthUserInfoVo))
        );
    }

    @Operation(summary = "Oauth SignIn API", description = "Oauth 로그인", tags = {"Oauth-service"})
    @PostMapping("/sign-in")
    public BaseResponseEntity<ResponseSignInVo> oauthLogin (
            @RequestBody RequestOauthUserInfoVo requestOauthUserInfoVo
            ) throws Exception {
        return new BaseResponseEntity<>(
                BaseResponseStatus.SIGN_IN_SUCCESS,
                oauthService.oauthSignIn(
                        RequestOauthUserInfoDto.from(requestOauthUserInfoVo)).toVo()
        );
    }

    @Operation(summary = "Oauth SignUp API", description = "Oauth 회원가입", tags = {"Oauth-service"})
    @PostMapping("/sign-up")
    public BaseResponseEntity<Void> oauthSignUp(
            @RequestBody RequestOauthSignUpVo requestOauthSignUpVo
            ) {
        oauthService.oauthSignUp(RequestOauthSignUpDto.from(requestOauthSignUpVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SIGN_UP_SUCCESS);
    }
}
