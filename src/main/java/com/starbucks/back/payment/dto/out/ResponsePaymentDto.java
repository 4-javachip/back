package com.starbucks.back.payment.dto.out;

import com.starbucks.back.payment.domain.Payment;
import com.starbucks.back.payment.domain.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponsePaymentDto {
    private Long id;
    private String userUuid;
    private String paymentUuid;
    private String paymentCode;
    private String couponUuid;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private PaymentStatus paymentStatus;
    private String paymentToken;
    private String failReason;
    private LocalDateTime approvedAt;

    @Builder
    public ResponsePaymentDto(
            Long id,
            String userUuid,
            String paymentUuid,
            String paymentCode,
            String couponUuid,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            PaymentStatus paymentStatus,
            String paymentToken,
            String failReason,
            LocalDateTime approvedAt
    ) {
        this.id = id;
        this.userUuid = userUuid;
        this.paymentUuid = paymentUuid;
        this.paymentCode = paymentCode;
        this.couponUuid = couponUuid;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.paymentStatus = paymentStatus;
        this.paymentToken = paymentToken;
        this.failReason = failReason;
        this.approvedAt = approvedAt;
    }

    // entity -> dto
    public static ResponsePaymentDto from(Payment payment) {
        return ResponsePaymentDto.builder()
                .id(payment.getId())
                .userUuid(payment.getUserUuid())
                .paymentUuid(payment.getPaymentUuid())
                .paymentCode(payment.getPaymentCode())
                .couponUuid(payment.getCouponUuid())
                .totalOriginPrice(payment.getTotalOriginPrice())
                .totalPurchasePrice(payment.getTotalPurchasePrice())
                .paymentStatus(payment.getStatus())
                .paymentToken(payment.getPaymentToken())
                .failReason(payment.getFailReason())
                .approvedAt(payment.getApprovedAt())
                .build();
    }
}
