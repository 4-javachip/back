package com.starbucks.back.payment.dto.in;

import com.starbucks.back.payment.domain.Payment;
import com.starbucks.back.payment.domain.PaymentStatus;
import com.starbucks.back.payment.vo.in.RequestPaymentConfirmVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RequestPaymentConfirmDto {
    private String userUuid;
    private String paymentCode;      // toss의 paymentKey
    private String paymentUuid;     // toss의 orderId
    private Integer totalPurchasePrice;    // toss의 amount

    @Builder
    public RequestPaymentConfirmDto(
            String userUuid,
            String paymentCode,
            String paymentUuid,
            Integer totalPurchasePrice
    ) {
        this.userUuid = userUuid;
        this.paymentCode = paymentCode;
        this.paymentUuid = paymentUuid;
        this.totalPurchasePrice = totalPurchasePrice;
    }

    public static RequestPaymentConfirmDto from(
            String userUuid,
            RequestPaymentConfirmVo requestPaymentConfirmVo
    ) {
        return RequestPaymentConfirmDto.builder()
                .userUuid(userUuid)
                .paymentCode(requestPaymentConfirmVo.getPaymentKey())
                .paymentUuid(requestPaymentConfirmVo.getOrderId())
                .totalPurchasePrice(requestPaymentConfirmVo.getAmount())
                .build();
    }

    public Payment updateSuccessPayment(
            Payment payment,
            String paymentCode,
            String method,
            PaymentStatus paymentStatus,
            LocalDateTime approvedAt
    ) {
        return Payment.builder()
                .id(payment.getId())
                .userUuid(userUuid)
                .paymentUuid(paymentUuid)
                .paymentCode(paymentCode)
                .totalOriginPrice(payment.getTotalOriginPrice())
                .totalPurchasePrice(totalPurchasePrice)
                .status(paymentStatus)
                .pgProvider("TOSS")
                .pgTid(payment.getPgTid())
                .paymentToken(payment.getPaymentToken())
                .failReason(payment.getFailReason())
                .approvedAt(approvedAt)
                .method(method)
                .orderName(payment.getOrderName())
                .orderListUuid(payment.getOrderListUuid())
                .build();
    }

    public Payment updateFailPayment(
            Payment payment,
            String failReason
    )
    {
        return Payment.builder()
                .id(payment.getId())
                .userUuid(userUuid)
                .paymentUuid(paymentUuid)
                .paymentCode(paymentCode)
                .method(payment.getMethod())
                .orderName(payment.getOrderName())
                .orderListUuid(payment.getOrderListUuid())
                .totalOriginPrice(payment.getTotalOriginPrice())
                .totalPurchasePrice(totalPurchasePrice)
                .status(PaymentStatus.ABORTED)
                .pgProvider("TOSS")
                .pgTid(payment.getPgTid())
                .paymentToken(payment.getPaymentToken())
                .failReason(failReason)
                .approvedAt(payment.getApprovedAt())
                .build();
    }

}
