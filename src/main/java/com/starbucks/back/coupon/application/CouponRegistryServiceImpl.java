package com.starbucks.back.coupon.application;

import com.starbucks.back.coupon.dto.in.RequestRegisterCouponDto;
import com.starbucks.back.coupon.infrastructure.CouponRegistryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponRegistryServiceImpl implements CouponRegistryService {

    private final CouponRegistryRepository couponRegistryRepository;

    @Override
    public void registerCoupon(RequestRegisterCouponDto requestRegisterCouponDto) {

    }

}
