package com.starbucks.back.payment.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "payment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_uuid", nullable = false, length = 50)
    private String userUuid;

    @Column(name = "payment_uuid", nullable = false, length = 50)
    private String paymentUuid;

    @Column(name = "payment_code", nullable = false, length = 50)
    private String paymentCode;

    @Column(name = "coupon_uuid", length = 50)
    private String couponUuid;

    @Column(name = "total_origin_price", nullable = false)
    private Integer totalOriginPrice;

    @Column(name = "total_purchase_price", nullable = false)
    private Integer totalPurchasePrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PaymentStatus status;

    @Column(name = "pg_provider", nullable = false, length = 30)
    private String pgProvider;

    @Column(name = "pg_tid", nullable = false, length = 100)
    private String pgTid;

    @Column(name = "payment_token", length = 100)
    private String paymentToken;

    @Column(name = "fail_reason", length = 255)
    private String failReason;

    @Column(name = "approved_at", nullable = true)
    private LocalDateTime approvedAt;

    @Column(name = "method", length = 50)
    private String method;

    @Column(name = "order_name", length = 100)
    private String orderName;

    @Column(name = "order_list_uuid", length = 50)
    private String orderListUuid;

    @Builder
    public Payment(
            Long id,
            String userUuid,
            String paymentUuid,
            String paymentCode,
            String couponUuid,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            PaymentStatus status,
            String pgProvider,
            String pgTid,
            String paymentToken,
            String failReason,
            LocalDateTime approvedAt,
            String method,
            String orderName,
            String orderListUuid
    ) {
        this.id = id;
        this.userUuid = userUuid;
        this.paymentUuid = paymentUuid;
        this.paymentCode = paymentCode;
        this.couponUuid = couponUuid;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.status = status;
        this.pgProvider = pgProvider;
        this.pgTid = pgTid;
        this.paymentToken = paymentToken;
        this.failReason = failReason;
        this.approvedAt = approvedAt;
        this.method = method;
        this.orderName = orderName;
        this.orderListUuid = orderListUuid;
    }
}
