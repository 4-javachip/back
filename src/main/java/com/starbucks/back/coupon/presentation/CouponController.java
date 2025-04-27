package com.starbucks.back.coupon.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.coupon.application.CouponService;
import com.starbucks.back.coupon.dto.in.RequestAddCouponDto;
import com.starbucks.back.coupon.dto.out.ResponseGetAvailableCouponDto;
import com.starbucks.back.coupon.vo.in.RequestAddCouponVo;
import com.starbucks.back.coupon.vo.out.ResponseGetAvailableCouponVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupon")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;
    private final SecurityUtil securityUtil;

    @GetMapping
    public BaseResponseEntity<List<ResponseGetAvailableCouponVo>> getAvailableCoupons() {

        return new BaseResponseEntity<>(
                couponService.getAvailableCoupons(securityUtil.getCurrentUserUuid())
                        .stream()
                        .map(ResponseGetAvailableCouponDto::toVo)
                        .toList()
        );
    }

    @PostMapping
    public BaseResponseEntity<Void> addCoupon(
            @RequestBody RequestAddCouponVo requestAddCouponVo
    ) {
        couponService.addCoupon(RequestAddCouponDto.from(requestAddCouponVo));
        return new BaseResponseEntity<>();
    }
}
