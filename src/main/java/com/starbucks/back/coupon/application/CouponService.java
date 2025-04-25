package com.starbucks.back.coupon.application;

import com.starbucks.back.coupon.domain.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponService {
    List<Coupon> getAllCoupon();
}
