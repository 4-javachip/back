package com.starbucks.back.payment.dto.in;

import com.starbucks.back.payment.domain.Payment;
import com.starbucks.back.payment.domain.PaymentStatus;
import com.starbucks.back.payment.vo.in.RequestPaymentCreateVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class RequestPaymentCreateDto {
    private String userUuid;
    private String paymentUuid;
    private Integer amount;
    private String method;
    private String orderName;
    private Integer saleAmount;
    private Integer totalAmount;

    // 아래 항목은 결제가 진행되면서 추가될 내용
    private String paymentCode;
    private PaymentStatus status;
    private String pgProvider;
    private String pgTid;
    private String paymentToken;
    private String failReason;
    private LocalDateTime approvedAt;

    @Builder
    public RequestPaymentCreateDto(
            String userUuid,
            String paymentUuid,
            Integer amount,
            String method,
            String orderName,
            Integer saleAmount,
            Integer totalAmount,
            String paymentCode,
            PaymentStatus status,
            String pgProvider,
            String pgTid,
            String paymentToken,
            String failReason,
            LocalDateTime approvedAt
    ) {
        this.userUuid = userUuid;
        this.paymentUuid = paymentUuid;
        this.amount = amount;
        this.method = method;
        this.orderName = orderName;
        this.saleAmount = saleAmount;
        this.totalAmount = totalAmount;
        this.paymentCode = paymentCode;
        this.status = status;
        this.pgProvider = pgProvider;
        this.pgTid = pgTid;
        this.paymentToken = paymentToken;
        this.failReason = failReason;
        this.approvedAt = approvedAt;
    }

    // vo => dto
    public static RequestPaymentCreateDto from(
            String userUuid,
            RequestPaymentCreateVo requestPaymentCreateVo
    ) {
        return RequestPaymentCreateDto.builder()
                .userUuid(userUuid)
                .paymentUuid(UUID.randomUUID().toString())
                .amount(requestPaymentCreateVo.getAmount())
                .method(requestPaymentCreateVo.getMethod())
                .orderName(requestPaymentCreateVo.getOrderName())
                .saleAmount(requestPaymentCreateVo.getSaleAmount())
                .totalAmount(requestPaymentCreateVo.getAmount() - requestPaymentCreateVo.getSaleAmount())
                .paymentCode("")
                .status(PaymentStatus.READY)
                .pgProvider("")
                .pgTid("")
                .paymentToken("")
                .failReason("")
                .approvedAt(LocalDateTime.now())
                .build();
    }

    // dto => entity
    public Payment toEntity() {
        return Payment.builder()
                .userUuid(userUuid)
                .paymentUuid(paymentUuid)
                .paymentCode(paymentCode)
                // amount, method, orderName은 Payment 테이블에 저장하지 않음. 차후 추가될 수도 있음
                .saleAmount(saleAmount)
                .totalAmount(totalAmount)
                .status(status)
                .pgProvider(pgProvider)
                .pgTid(pgTid)
                .paymentToken(paymentToken)
                .failReason(failReason)
                .approvedAt(approvedAt)
                .build();
    }
}
