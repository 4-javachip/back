package com.starbucks.back.coupon.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseGetMyCouponsVo {
    private String couponUuid;

    @Builder
    public ResponseGetMyCouponsVo(String couponUuid) {
        this.couponUuid = couponUuid;
    }
}
