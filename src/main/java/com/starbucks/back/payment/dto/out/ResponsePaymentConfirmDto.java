package com.starbucks.back.payment.dto.out;

import com.starbucks.back.payment.domain.PaymentStatus;
import com.starbucks.back.payment.vo.in.RequestPaymentConfirmVo;
import com.starbucks.back.payment.vo.out.ResponsePaymentConfirmVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponsePaymentConfirmDto {

    private String message;
    private String paymentUuid;
    private PaymentStatus status;
    private String approvedAt;

    @Builder
    public ResponsePaymentConfirmDto(
            String message,
            String paymentUuid,
            PaymentStatus status,
            String approvedAt
    ) {
        this.message = message;
        this.paymentUuid = paymentUuid;
        this.status = status;
        this.approvedAt = approvedAt;
    }

    public static ResponsePaymentConfirmDto from(
            String message,
            String paymentUuid,
            PaymentStatus status,
            String approvedAt
    ) {
        return ResponsePaymentConfirmDto.builder()
                .message(message)
                .paymentUuid(paymentUuid)
                .status(status)
                .approvedAt(approvedAt)
                .build();
    }

    public static ResponsePaymentConfirmVo toVo(
            ResponsePaymentConfirmDto responsePaymentConfirmDto
    ) {
        return ResponsePaymentConfirmVo.builder()
                .message(responsePaymentConfirmDto.getMessage())
                .paymentUuid(responsePaymentConfirmDto.getPaymentUuid())
                .paymentStatus(responsePaymentConfirmDto.getStatus())
                .approvedAt(responsePaymentConfirmDto.getApprovedAt())
                .build();
    }
}
