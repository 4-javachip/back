package com.starbucks.back.coupon.dto.out;

import com.starbucks.back.coupon.domain.CouponRegistry;
import com.starbucks.back.coupon.vo.out.ResponseGetMyCouponsVo;
import lombok.Builder;

public class ResponseGetMyCouponsDto {
    private String couponUuid;

    @Builder
    public ResponseGetMyCouponsDto(String couponUuid) {
        this.couponUuid = couponUuid;
    }

    public static ResponseGetMyCouponsDto from(CouponRegistry couponRegistry) {
        return ResponseGetMyCouponsDto.builder()
                .couponUuid(couponRegistry.getCouponUuid())
                .build();
    }

    public ResponseGetMyCouponsVo toVo() {
        return ResponseGetMyCouponsVo.builder()
                .couponUuid(couponUuid)
                .build();
    }
}
