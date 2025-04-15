package com.starbucks.back.payment.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.payment.application.PaymentService;
import com.starbucks.back.payment.dto.in.RequestPaymentConfirmDto;
import com.starbucks.back.payment.dto.in.RequestPaymentCreateDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentConfirmDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentCreateDto;
import com.starbucks.back.payment.vo.in.RequestPaymentConfirmVo;
import com.starbucks.back.payment.vo.in.RequestPaymentCreateVo;
import com.starbucks.back.payment.vo.out.ResponsePaymentConfirmVo;
import com.starbucks.back.payment.vo.out.ResponsePaymentCreateVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public BaseResponseEntity<ResponsePaymentCreateVo> addPayment(
           @RequestBody RequestPaymentCreateVo requestPaymentCreateVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();
        ResponsePaymentCreateDto responsePaymentCreateDto = paymentService.addPayment(
                RequestPaymentCreateDto.from(userUuid, requestPaymentCreateVo)
        );
        return new BaseResponseEntity<>(responsePaymentCreateDto.toVo());
    }

    /**
     * 결제 승인
     */
    @PostMapping("/confirm")
    @Operation(summary = "ConfirmPayment API", description = "결제 승인 API 입니다.", tags = {"Payment-Service"})
    public BaseResponseEntity<ResponsePaymentConfirmVo> confirmPayment(
            @RequestBody RequestPaymentConfirmVo requestPaymentConfirmVo
            ) {
        String userUuid = securityUtil.getCurrentUserUuid();
        ResponsePaymentConfirmDto responsePaymentConfirmDto = paymentService.confirmPayment(
                RequestPaymentConfirmDto.from(userUuid, requestPaymentConfirmVo)
        );
        return new BaseResponseEntity<>(responsePaymentConfirmDto.toVo(responsePaymentConfirmDto));
    }

}
