package com.starbucks.back.payment.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.payment.application.PaymentService;
import com.starbucks.back.payment.dto.in.RequestPaymentCreateDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentCreateDto;
import com.starbucks.back.payment.vo.in.RequestPaymentCreateVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final SecurityUtil securityUtil;

    /**
     * 결제 생성
     */
    @PostMapping
    @Operation(summary = "CreatePayment API", description = "결제 생성(결제창 호출) API 입니다.", tags = {"Payment-Service"})
    public BaseResponseEntity<ResponsePaymentCreateDto> addPayment(
           RequestPaymentCreateVo requestPaymentCreateVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();
        ResponsePaymentCreateDto result = paymentService.addPayment(
                RequestPaymentCreateDto.from(userUuid, requestPaymentCreateVo)
        );
        return new BaseResponseEntity<>(result);
    }
}
