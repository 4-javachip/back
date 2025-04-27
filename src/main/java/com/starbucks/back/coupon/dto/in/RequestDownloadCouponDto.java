package com.starbucks.back.coupon.dto.in;

import com.starbucks.back.coupon.domain.CouponRegistry;
import com.starbucks.back.coupon.domain.enums.CouponState;
import com.starbucks.back.coupon.vo.in.RequestDownloadCouponVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestDownloadCouponDto {
    private String couponUuid;
    private String userUuid;

    @Builder
    public RequestDownloadCouponDto(String couponUuid, String userUuid) {
        this.couponUuid = couponUuid;
        this.userUuid = userUuid;
    }

    public static RequestDownloadCouponDto of(RequestDownloadCouponVo requestDownloadCouponVo, String userUuid) {
        return RequestDownloadCouponDto.builder()
                .couponUuid(requestDownloadCouponVo.getCouponUuid())
                .userUuid(userUuid)
                .build();
    }

    public CouponRegistry toEntity() {
        return CouponRegistry.builder()
                .couponUuid(couponUuid)
                .userUuid(userUuid)
                .state(CouponState.UNUSED)
                .build();
    }
}
