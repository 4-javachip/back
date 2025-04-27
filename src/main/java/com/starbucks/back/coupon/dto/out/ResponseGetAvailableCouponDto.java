package com.starbucks.back.coupon.dto.out;

import com.starbucks.back.coupon.domain.Coupon;
import com.starbucks.back.coupon.domain.enums.DiscountType;
import com.starbucks.back.coupon.vo.out.ResponseGetAvailableCouponVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ResponseGetAvailableCouponDto {

    private String couponUuid;
    private String name;
    private DiscountType discountType;
    private Integer discountAmount;
    private Integer maxDiscountAmount;
    private LocalDate validFrom;
    private LocalDate validUntil;

    @Builder
    public ResponseGetAvailableCouponDto(
            String couponUuid, String name, DiscountType discountType, Integer discountAmount,
            Integer maxDiscountAmount, LocalDate validFrom, LocalDate validUntil) {
        this.couponUuid = couponUuid;
        this.name = name;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.maxDiscountAmount = maxDiscountAmount;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    public static ResponseGetAvailableCouponDto from(Coupon coupon) {
        return ResponseGetAvailableCouponDto.builder()
                .couponUuid(coupon.getCouponUuid())
                .name(coupon.getName())
                .discountType(coupon.getDiscountType())
                .discountAmount(coupon.getDiscountAmount())
                .maxDiscountAmount(coupon.getMaxDiscountAmount())
                .validFrom(coupon.getValidFrom())
                .validUntil(coupon.getValidUntil())
                .build();
    }

    public ResponseGetAvailableCouponVo toVo() {
        return ResponseGetAvailableCouponVo.builder()
                .couponUuid(couponUuid)
                .name(name)
                .discountType(discountType.name())
                .discountAmount(discountAmount)
                .maxDiscountAmount(maxDiscountAmount)
                .validFrom(validFrom.toString())
                .validUntil(validUntil.toString())
                .build();
    }
}
