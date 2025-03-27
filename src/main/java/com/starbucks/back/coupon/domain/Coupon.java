package com.starbucks.back.coupon.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "coupon")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 쿠폰 UUID
     */
    @Column(name = "coupon_uuid", length = 50, nullable = false, unique = true)
    private String couponUuid;

    /**
     * 쿠폰 이름
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 카테고리 id
     */
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    /**
     * 최소 주문 금액
     */
    @Column(name = "minimum_order_amount", nullable = false)
    private Integer minimumOrderAmount;

    /**
     * 최대 할인 금액
     */
    @Column(name = "maximum_discount_amount", nullable = false)
    private Integer maximumDiscountAmount;

    /**
     * 쿠폰 타입
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_type", nullable = false)
    private CouponType couponType;

    /**
     * 쿠폰 상태
     */
    @Column(name = "state", nullable = false)
    private Boolean state;

    /**
     * 사용 가능 시점
     */
    @Column(name = "valid_from", nullable = false, columnDefinition = "DATETIME(0)")
    private LocalDateTime validFrom;

    /**
     * 사용 종료 시점
     */
    @Column(name = "valid_until", nullable = false, columnDefinition = "DATETIME(0)")
    private LocalDateTime validUntil;

    @Builder
    public Coupon(Long id, String couponUuid, String name, Integer categoryId,
                  Integer minimumOrderAmount, Integer maximumDiscountAmount,
                  CouponType couponType, Boolean state, LocalDateTime validFrom,
                  LocalDateTime validUntil) {
        this.id = id;
        this.couponUuid = couponUuid;
        this.name = name;
        this.categoryId = categoryId;
        this.minimumOrderAmount = minimumOrderAmount;
        this.maximumDiscountAmount = maximumDiscountAmount;
        this.couponType = couponType;
        this.state = state;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

}
