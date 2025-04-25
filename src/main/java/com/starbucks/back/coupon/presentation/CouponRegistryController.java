package com.starbucks.back.coupon.presentation;

import com.starbucks.back.coupon.application.CouponRegistryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coupon-registry")
@RequiredArgsConstructor
public class CouponRegistryController {
    private final CouponRegistryService couponRegistryService;
}
