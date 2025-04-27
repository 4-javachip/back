package com.starbucks.back.coupon.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseGetAvailableCouponVo {
    private String couponUuid;
    private String name;
    private String discountType;
    private Integer discountAmount;
    private Integer minOrderAmount;
    private Integer maxDiscountAmount;
    private String validFrom;
    private String validUntil;

    @Builder
    public ResponseGetAvailableCouponVo
            (String couponUuid, String name, String discountType, Integer discountAmount,
             Integer minOrderAmount, Integer maxDiscountAmount,
             String validFrom, String validUntil) {
        this.couponUuid = couponUuid;
        this.name = name;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.minOrderAmount = minOrderAmount;
        this.maxDiscountAmount = maxDiscountAmount;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }
}
