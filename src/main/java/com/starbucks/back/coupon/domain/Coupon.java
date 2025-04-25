package com.starbucks.back.coupon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starbucks.back.common.entity.SoftDeletableEntity;
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
public class Coupon extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "coupon_uuid", length = 50, nullable = false, unique = true)
    private String couponUuid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "discount_amount", nullable = false)
    private Integer discountAmount;

    @Column(name = "valid_from", nullable = false, columnDefinition = "DATETIME(0)")
    private LocalDateTime validFrom;

    @Column(name = "valid_until", nullable = false, columnDefinition = "DATETIME(0)")
    private LocalDateTime validUntil;

    @Column(name = "stock")
    private Integer stock;

    @Builder
    public Coupon(Long id, String couponUuid, String name, Integer discountAmount,
                  LocalDateTime validFrom, LocalDateTime validUntil, Integer stock) {
        this.id = id;
        this.couponUuid = couponUuid;
        this.name = name;
        this.discountAmount = discountAmount;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.stock = stock;
    }

}
