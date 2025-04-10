package com.starbucks.back.user.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.user.application.UserService;
import com.starbucks.back.user.dto.in.RequestMatchPasswordDto;
import com.starbucks.back.user.dto.in.RequestResetPasswordDto;
import com.starbucks.back.user.dto.in.RequestUpdateNicknameDto;
import com.starbucks.back.user.dto.in.RequestUpdatePasswordDto;
import com.starbucks.back.user.vo.in.RequestMatchPasswordVo;
import com.starbucks.back.user.vo.in.RequestResetPasswordVo;
import com.starbucks.back.user.vo.in.RequestUpdateNicknameVo;
import com.starbucks.back.user.vo.in.RequestUpdatePasswordVo;
import com.starbucks.back.user.vo.out.ResponseGetUserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SecurityUtil securityUtil;

    @Operation(summary = "Get User Info API", description = "유저 정보 조회", tags = {"User-service"})
    @GetMapping()
    public BaseResponseEntity<ResponseGetUserInfoVo> getUserInfo() {
        return new BaseResponseEntity<>(
                userService.getUserInfo(securityUtil.getCurrentUserUuid()).toVo()
        );
    }

    @Operation(summary = "Get User Details API", description = "유저 상세 정보 조회", tags = {"User-service"})
    @GetMapping("/detail")
    public BaseResponseEntity<UserDetails> getUserDetails() {
        return new BaseResponseEntity<>(
                securityUtil.getCurrentUserDetails()
        );
    }

    @Operation(summary = "Match CurrentPassword API", description = "비밀번호 변경을 위한 현재 비밀번호 확인", tags = {"User-service"})
    @PostMapping("/current-password")
    public BaseResponseEntity<Void> authenticateCurrentPassword(
            @Valid @RequestBody RequestMatchPasswordVo requestMatchPasswordVo
            ) {
        userService.authenticateCurrentPassword(
                RequestMatchPasswordDto.of(securityUtil.getCurrentUserUuid(), requestMatchPasswordVo)
        );

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS_MATCH_PASSWORD);
    }

    @Operation(summary = "Update Password API", description = "로그인 된 상태에서 비밀번호 변경", tags = {"User-service"})
    @PostMapping("/password/update")
    public BaseResponseEntity<Void> updatePassword(
            @Valid @RequestBody RequestUpdatePasswordVo requestUpdatePasswordVo
    ) {
        userService.updatePassword(
                RequestUpdatePasswordDto.of(securityUtil.getCurrentUserUuid() ,requestUpdatePasswordVo)
        );

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS_UPDATE_PASSWORD);
    }

    @Operation(summary = "Reset Password API", description = "비밀번호 재설정", tags = {"User-service"})
    @PostMapping("/password/reset")
    public BaseResponseEntity<Void> resetPassword(
            @Valid @RequestBody RequestResetPasswordVo requestResetPasswordVo
    ) {
        userService.resetPassword(
                RequestResetPasswordDto.from(requestResetPasswordVo)
        );

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS_UPDATE_PASSWORD);
    }

    @Operation(summary = "Update Nickname API", description = "닉네임 변경", tags = {"User-service"})
    @PostMapping("/nickname/update")
    public BaseResponseEntity<Void> updateNickname(
            @Valid @RequestBody RequestUpdateNicknameVo requestUpdateNicknameVo
    ) {
        userService.updateNickname(
                RequestUpdateNicknameDto.of(securityUtil.getCurrentUserUuid(), requestUpdateNicknameVo)
        );

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS_UPDATE_NICKNAME);
    }


}
