package com.starbucks.back.coupon.application;

import com.starbucks.back.coupon.domain.Coupon;
import com.starbucks.back.coupon.dto.in.RequestAddCouponDto;
import com.starbucks.back.coupon.dto.out.ResponseGetAvailableCouponDto;

import java.util.List;
import java.util.Optional;

public interface CouponService {
    List<ResponseGetAvailableCouponDto> getAvailableCoupons(String userUuid);
    void addCoupon(RequestAddCouponDto requestAddCouponDto);
}
