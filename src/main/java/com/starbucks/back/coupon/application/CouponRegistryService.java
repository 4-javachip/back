package com.starbucks.back.coupon.application;

import com.starbucks.back.coupon.dto.in.RequestRegisterCouponDto;

public interface CouponRegistryService {
    void registerCoupon(RequestRegisterCouponDto requestRegisterCouponDto);
}
