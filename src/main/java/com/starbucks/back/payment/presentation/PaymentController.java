package com.starbucks.back.payment.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.payment.application.PaymentService;
import com.starbucks.back.payment.domain.PaymentStatus;
import com.starbucks.back.payment.dto.in.RequestPaymentConfirmDto;
import com.starbucks.back.payment.dto.in.RequestPaymentCreateDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentConfirmDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentCreateDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentDto;
import com.starbucks.back.payment.vo.in.RequestPaymentConfirmVo;
import com.starbucks.back.payment.vo.in.RequestPaymentCreateVo;
import com.starbucks.back.payment.vo.out.ResponsePaymentConfirmVo;
import com.starbucks.back.payment.vo.out.ResponsePaymentCreateVo;
import com.starbucks.back.payment.vo.out.ResponsePaymentVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    /**
     * 가상계좌 웹훅
     */
    @PostMapping("/webhook")
    @Operation(summary = "VirtualAccountWebhook API", description = "가상계좌 웹훅 API 입니다.", tags = {"Payment-Service"})
    public BaseResponseEntity<Void> virtualAccountWebhook(
            @RequestBody Map<String, Object> payload
    ) {

        String paymentUuid = (String) payload.get("orderId");
        PaymentStatus status = PaymentStatus.from((String) payload.get("status"));
        // DB에 paymentStatus 업데이트
        paymentService.updatePaymentStatus(paymentUuid, status);

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 결제 내역 조회
     */
    @GetMapping("/{paymentUuid}")
    @Operation(summary = "GetPayment API", description = "결제 내역 조회 API 입니다.", tags = {"Payment-Service"})
    public BaseResponseEntity<ResponsePaymentVo> getPayment(
            @PathVariable("paymentUuid") String paymentUuid
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();
        ResponsePaymentDto responsePaymentDto = paymentService.getPayment(paymentUuid);
        return new BaseResponseEntity<>(responsePaymentDto.toVo());
    }
}
