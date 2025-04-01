package com.starbucks.back.agreement.presentation;

import com.starbucks.back.agreement.application.AgreementService;
import com.starbucks.back.agreement.dto.out.ResponseGetAgreementDto;
import com.starbucks.back.agreement.vo.out.ResponseGetAgreementVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agreement")
@RequiredArgsConstructor
public class AgreementController {

    private final AgreementService agreementService;

    @Operation(summary = "Get SignUp Agreements API", description = "회원가입 관련 약관 가져오기", tags = {"Agreement-service"})
    @GetMapping("/sign-up")
    public BaseResponseEntity<List<ResponseGetAgreementVo>> getSignUpAgreements() {
           return new BaseResponseEntity<>(
                    agreementService.getSignUpAgreements()
                            .stream()
                            .map(ResponseGetAgreementDto::toVo)
                            .toList()
            );
    }

    @Operation(summary = "Get Shipping Address Agreements API", description = "배송지 관련 약관 가져오기", tags = {"Agreement-service"})
    @GetMapping("/shipping-address")
    public BaseResponseEntity<List<ResponseGetAgreementVo>> getShippingAddressAgreements() {
        return new BaseResponseEntity<>(
                agreementService.getShippingAddressAgreements()
                        .stream()
                        .map(ResponseGetAgreementDto::toVo)
                        .toList()
        );
    }

}
