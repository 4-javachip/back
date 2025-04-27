package com.starbucks.back.coupon.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestDownloadCouponVo {
    @NotBlank(message = "Coupon UUID는 필수입니다.")
    private String couponUuid;
}
