package com.starbucks.back.coupon.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestUseCouponVo {
    @NotBlank(message = "쿠폰 UUID는 필수입니다.")
    private String couponUuid;
}
