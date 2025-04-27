package com.starbucks.back.coupon.dto.in;

import com.starbucks.back.coupon.domain.CouponRegistry;
import com.starbucks.back.coupon.domain.enums.CouponState;
import com.starbucks.back.coupon.vo.in.RequestUseCouponVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestUseCouponDto {
    private String couponUuid;
    private String userUuid;

    @Builder
    public RequestUseCouponDto(String couponUuid, String userUuid) {
        this.couponUuid = couponUuid;
        this.userUuid = userUuid;
    }

    public static RequestUseCouponDto of(RequestUseCouponVo requestUseCouponVo, String userUuid) {
        return RequestUseCouponDto.builder()
                .couponUuid(requestUseCouponVo.getCouponUuid())
                .userUuid(userUuid)
                .build();
    }

    public CouponRegistry toEntity(CouponRegistry couponRegistry) {
        return CouponRegistry.builder()
                .id(couponRegistry.getId())
                .couponUuid(couponUuid)
                .userUuid(userUuid)
                .state(CouponState.USED)
                .build();
    }
}
