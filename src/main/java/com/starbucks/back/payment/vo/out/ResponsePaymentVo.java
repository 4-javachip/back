package com.starbucks.back.payment.vo.out;

import com.starbucks.back.payment.domain.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponsePaymentVo {

    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private Integer totalSalePrice;
    private PaymentStatus paymentStatus;
    private String method;
    private LocalDateTime approvedAt;

    @Builder
    public ResponsePaymentVo(
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            Integer totalSalePrice,
            PaymentStatus paymentStatus,
            String method,
            LocalDateTime approvedAt
    ) {
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.totalSalePrice = totalSalePrice;
        this.paymentStatus = paymentStatus;
        this.method = method;
        this.approvedAt = approvedAt;
    }
}
