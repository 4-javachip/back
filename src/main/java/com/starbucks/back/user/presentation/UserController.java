package com.starbucks.back.user.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.user.application.UserService;
import com.starbucks.back.user.dto.in.RequestExistsEmailDto;
import com.starbucks.back.user.dto.in.RequestExistsNicknameDto;
import com.starbucks.back.user.dto.in.RequestSignUpDto;
import com.starbucks.back.user.vo.in.RequestExistsEmailVo;
import com.starbucks.back.user.vo.in.RequestExistsNicknameVo;
import com.starbucks.back.user.vo.in.RequestSignUpVo;
import com.starbucks.back.user.vo.in.RequestUpdateNickname;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "SignUp API", description = "회원가입", tags = {"User-service"})
    @PostMapping("/sign-up")
    public BaseResponseEntity<Void> signUp(@Valid @RequestBody RequestSignUpVo requestSignUpVo) {
        userService.signUp(RequestSignUpDto.from(requestSignUpVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "Check Email API", description = "이메일 중복 확인", tags = {"User-service"})
    @PostMapping("/email/exists")
    public BaseResponseEntity<Boolean> emailExists(@Valid @RequestBody RequestExistsEmailVo requestExistsEmailVo) {
        return new BaseResponseEntity<>(
                userService.existsEmail(RequestExistsEmailDto.from(requestExistsEmailVo).getEmail())
        );
    }

    @Operation(summary = "Check Nickname API", description = "닉네임 중복 확인", tags = {"User-service"})
    @PostMapping("/nickname/exists")
    public BaseResponseEntity<Boolean> nicknameExists(@Valid @RequestBody RequestExistsNicknameVo requestExistsNicknameVo) {
        return new BaseResponseEntity<>(
                userService.existsNickname(RequestExistsNicknameDto.from(requestExistsNicknameVo).getNickname())
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
