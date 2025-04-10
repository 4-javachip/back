package com.starbucks.back.oauth.presentation;

import com.starbucks.back.auth.vo.out.ResponseSignInVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.oauth.application.OauthService;
import com.starbucks.back.oauth.dto.in.RequestOauthSignUpDto;
import com.starbucks.back.oauth.dto.in.RequestOauthUserInfoDto;
import com.starbucks.back.oauth.dto.out.ResponseOauthUserInfoDto;
import com.starbucks.back.oauth.vo.in.RequestOauthSignUpVo;
import com.starbucks.back.oauth.vo.in.RequestOauthUserInfoVo;
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

    @PostMapping("/user-info")
    public BaseResponseEntity<ResponseOauthUserInfoDto> getOauthUserInfo(
            @RequestBody @Valid RequestOauthUserInfoVo requestOauthUserInfoVo
            ) throws Exception {
        return new BaseResponseEntity<>(
                oauthService.getOauthUserInfo(
                        RequestOauthUserInfoDto.from(requestOauthUserInfoVo))
        );
    }

    @PostMapping("/sign-in")
    public BaseResponseEntity<ResponseSignInVo> oauthLogin (
            @RequestBody RequestOauthUserInfoVo requestOauthUserInfoVo
            ) throws Exception {
        return new BaseResponseEntity<>(
                oauthService.oauthSignIn(
                        RequestOauthUserInfoDto.from(requestOauthUserInfoVo)
                        ).toVo()
        );
    }

    @PostMapping("/sign-up")
    public BaseResponseEntity<Void> oauthSignUp(
            @RequestBody RequestOauthSignUpVo requestOauthSignUpVo
            ) {
        oauthService.oauthSignUp(RequestOauthSignUpDto.from(requestOauthSignUpVo));
        return new BaseResponseEntity<>();
    }
}
