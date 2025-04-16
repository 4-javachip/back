package com.starbucks.back.payment.vo.out;

import com.starbucks.back.payment.domain.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponsePaymentConfirmVo {

    private String message;
    private String paymentUuid;
    private PaymentStatus paymentStatus;
    private String approvedAt;

    @Builder
    public ResponsePaymentConfirmVo(
            String message,
            String paymentUuid,
            PaymentStatus paymentStatus,
            String approvedAt
    ) {
        this.message = message;
        this.paymentUuid = paymentUuid;
        this.paymentStatus = paymentStatus;
        this.approvedAt = approvedAt;
    }
}
