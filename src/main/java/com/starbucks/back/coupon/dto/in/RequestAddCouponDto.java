package com.starbucks.back.coupon.dto.in;

import com.starbucks.back.coupon.domain.Coupon;
import com.starbucks.back.coupon.domain.enums.DiscountType;
import com.starbucks.back.coupon.vo.in.RequestAddCouponVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class RequestAddCouponDto {
    private String name;
    private DiscountType discountType;
    private Integer discountAmount;
    private Integer maxDiscountAmount;
    private LocalDate validFrom;
    private LocalDate validUntil;
    private String supply; // null 가능

    @Builder
    public RequestAddCouponDto(
            String name, DiscountType discountType, Integer discountAmount,
            Integer maxDiscountAmount, LocalDate validFrom,
            LocalDate validUntil, String supply) {
        this.name = name;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.maxDiscountAmount = maxDiscountAmount;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.supply = supply;
    }

    public static RequestAddCouponDto from(RequestAddCouponVo requestAddCouponVo) {
        return RequestAddCouponDto.builder()
                .name(requestAddCouponVo.getName())
                .discountType(DiscountType.valueOf(requestAddCouponVo.getDiscountType().toUpperCase()))
                .discountAmount(requestAddCouponVo.getDiscountAmount())
                .maxDiscountAmount(requestAddCouponVo.getMaxDiscountAmount())
                .validFrom(requestAddCouponVo.getValidFrom())
                .validUntil(requestAddCouponVo.getValidUntil())
                .supply(requestAddCouponVo.getSupply())
                .build();
    }

    public Coupon toEntity() {
        return Coupon.builder()
                .couponUuid(UUID.randomUUID().toString())
                .name(name)
                .discountType(discountType)
                .discountAmount(discountAmount)
                .maxDiscountAmount(maxDiscountAmount)
                .validFrom(validFrom)
                .validUntil(validUntil)
                .supply(supply)
                .build();
    }
}
