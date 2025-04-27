package com.starbucks.back.coupon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starbucks.back.common.entity.SoftDeletableEntity;
import com.starbucks.back.coupon.domain.enums.DiscountType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "coupon")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class  Coupon extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "coupon_uuid", length = 50, nullable = false, unique = true)
    private String couponUuid;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false)
    private DiscountType discountType;

    @Column(name = "discount_amount", nullable = false)
    private Integer discountAmount;

    @Column(name = "max_discount_amount", nullable = false)
    private Integer maxDiscountAmount;

    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;

    @Column(name = "valid_until", nullable = false)
    private LocalDate validUntil;

    @Column(name = "supply")
    private String supply;

    @Builder
    public Coupon(Long id, String couponUuid, String name, DiscountType discountType, Integer discountAmount,
                  Integer maxDiscountAmount, LocalDate validFrom, LocalDate validUntil, String supply) {
        this.id = id;
        this.couponUuid = couponUuid;
        this.name = name;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.maxDiscountAmount = maxDiscountAmount;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.supply = supply;
    }

}
