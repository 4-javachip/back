package com.starbucks.back.agreement.presentation;

import com.starbucks.back.agreement.application.UserAgreementService;
import com.starbucks.back.agreement.dto.in.RequestAddUserAgreementDto;
import com.starbucks.back.agreement.dto.out.ResponseGetUserAgreementDto;
import com.starbucks.back.agreement.vo.in.RequestAddUserAgreementVo;
import com.starbucks.back.agreement.vo.out.ResponseGetUserAgreementVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-agreement")
@RequiredArgsConstructor
public class    UserAgreementController {

    private final UserAgreementService userAgreementService;
    private final SecurityUtil securityUtil;

    @Operation(summary = "Add User Agreement API", description = "유저 약관 동의&비동의 등록&수정", tags = {"UserAgreement-service"})
    @PostMapping
    public BaseResponseEntity<Void> addUserAgreement(
            @Valid @RequestBody RequestAddUserAgreementVo requestAddUserAgreementVo
    ) {
        userAgreementService.addUserAgreement(RequestAddUserAgreementDto.of(
                securityUtil.getCurrentUserUuid(), requestAddUserAgreementVo
                )
        );
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "Get User Agreements API", description = "유저 uuid로 유저-약관 리스트 가져오기", tags = {"UserAgreement-service"})
    @GetMapping
    public BaseResponseEntity<List<ResponseGetUserAgreementVo>> getUserAgreementsByUserUuid() {
        return new BaseResponseEntity<>(
                userAgreementService.getUserAgreementsByUserUuid(securityUtil.getCurrentUserUuid())
                        .stream()
                        .map(ResponseGetUserAgreementDto::toVo)
                        .toList()
        );
    }

    @Operation(summary = "Get User Agreement API", description = "유저-약관 uuid로 특정 유저-약관 가져오기", tags = {"UserAgreement-service"})
    @GetMapping("/{user-agreement-uuid}")
    public BaseResponseEntity<ResponseGetUserAgreementVo> getUserAgreementByUserAgreementUuid(
            @PathVariable("user-agreement-uuid") String userAgreementUuid
    ) {
        return new BaseResponseEntity<>(
                userAgreementService.getUserAgreementByUserAgreementUuid(userAgreementUuid)
                        .toVo()
        );
    }

    @Operation(summary = "Get User Shipping Address Agreement API", description = "유저 uuid로 유저-배송지 약관 가져오기", tags = {"UserAgreement-service"})
    @GetMapping("/shipping-address")
    public BaseResponseEntity<List<ResponseGetUserAgreementVo>> getUserShippingAddressAgreementByUserUuid() {
        return new BaseResponseEntity<>(
                userAgreementService.getUserShippingAddressAgreementByUserUuid(securityUtil.getCurrentUserUuid())
                        .stream()
                        .map(ResponseGetUserAgreementDto::toVo)
                        .toList()
        );
    }

    @Operation(summary = "Get User SignUp Agreement API", description = "유저 uuid로 유저-회원가입 약관 가져오기", tags = {"UserAgreement-service"})
    @GetMapping("/sign-up")
    public BaseResponseEntity<List<ResponseGetUserAgreementVo>> getUserSignUpAgreementByUserUuid() {
        return new BaseResponseEntity<>(
                userAgreementService.getUserSignUpAgreementByUserUuid(securityUtil.getCurrentUserUuid())
                        .stream()
                        .map(ResponseGetUserAgreementDto::toVo)
                        .toList()
        );
    }

}
