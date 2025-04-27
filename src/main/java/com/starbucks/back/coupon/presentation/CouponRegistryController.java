package com.starbucks.back.coupon.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.coupon.application.CouponRegistryService;
import com.starbucks.back.coupon.dto.in.RequestDownloadCouponDto;
import com.starbucks.back.coupon.dto.in.RequestUseCouponDto;
import com.starbucks.back.coupon.dto.out.ResponseGetMyCouponsDto;
import com.starbucks.back.coupon.vo.in.RequestDownloadCouponVo;
import com.starbucks.back.coupon.vo.in.RequestUseCouponVo;
import com.starbucks.back.coupon.vo.out.ResponseGetMyCouponsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupon-registry")
@RequiredArgsConstructor
public class CouponRegistryController {
    private final CouponRegistryService couponRegistryService;
    private final SecurityUtil securityUtil;

    @PostMapping("/download")
    public BaseResponseEntity<Void> downloadCoupon(
            @RequestBody RequestDownloadCouponVo requestDownloadCouponVo
            ) {
        couponRegistryService.downloadCoupon(
                RequestDownloadCouponDto.of(requestDownloadCouponVo, securityUtil.getCurrentUserUuid())
        );
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS_DOWNLOAD_COUPON);
    }

    @GetMapping
    public BaseResponseEntity<List<ResponseGetMyCouponsVo>> getMyCoupons() {
        return new BaseResponseEntity<>(
                couponRegistryService.getMyCoupons(securityUtil.getCurrentUserUuid())
                        .stream()
                        .map(ResponseGetMyCouponsDto::toVo)
                        .toList()
        );
    }

    @PostMapping("/use")
    public BaseResponseEntity<Void> useCoupon(
            @RequestBody RequestUseCouponVo requestUseCouponVo
            ) {
        couponRegistryService.useCoupon(
                RequestUseCouponDto.of(requestUseCouponVo, securityUtil.getCurrentUserUuid())
        );
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS_USE_COUPON);
    }
}
