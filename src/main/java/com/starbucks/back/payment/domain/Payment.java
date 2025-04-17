package com.starbucks.back.payment.domain;

import com.starbucks.back.payment.domain.PaymentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "approved_at", nullable = false)
    private LocalDateTime approvedAt;

    @Builder
    public Payment(
            Long id,
            String userUuid,
            String paymentUuid,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            String paymentCode,
            PaymentStatus status,
            String pgProvider,
            String pgTid,
            String paymentToken,
            String failReason,
            LocalDateTime approvedAt
    ) {
        this.id = id;
        this.userUuid = userUuid;
        this.paymentUuid = paymentUuid;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.paymentCode = paymentCode;
        this.status = status;
        this.pgProvider = pgProvider;
        this.pgTid = pgTid;
        this.paymentToken = paymentToken;
        this.failReason = failReason;
        this.approvedAt = approvedAt;
    }
}
