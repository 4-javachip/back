package com.starbucks.back.user.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.user.application.UserService;
import com.starbucks.back.user.domain.User;
import com.starbucks.back.user.vo.out.ResponseGetUserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
                userService.getUserInfo(
                        securityUtil.getCurrentUserUuid()
                ).toVo()
        );
    }

    @GetMapping("/detail")
    public BaseResponseEntity<UserDetails> getUserDetails() {
        return new BaseResponseEntity<>(
                securityUtil.getCurrentUserDetails()
        );
    }


//    @Operation(summary = "Update Nickname API", description = "닉네임 변경", tags = {"User-service"})
//    @PatchMapping("/nickname/update")
//    public BaseResponseEntity<Void> updateNickname(
//            @RequestHeader("Uuid") String userUuid,
//            @Valid @RequestBody RequestUpdateNickname requestUpdateNickname
//    ) {
//        userService.updateNickname(userUuid, requestUpdateNickname);
//        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
//    }


}
