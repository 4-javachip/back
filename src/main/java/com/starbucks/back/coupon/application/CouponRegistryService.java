package com.starbucks.back.coupon.application;


import com.starbucks.back.coupon.dto.in.RequestDownloadCouponDto;
import com.starbucks.back.coupon.dto.in.RequestUseCouponDto;
import com.starbucks.back.coupon.dto.out.ResponseGetMyCouponsDto;

import java.util.List;

public interface CouponRegistryService {
    void downloadCoupon(RequestDownloadCouponDto requestDownloadCouponDto);
    List<ResponseGetMyCouponsDto> getMyCoupons(String userUuid);
    void useCoupon(RequestUseCouponDto requestUseCouponDto);
}
