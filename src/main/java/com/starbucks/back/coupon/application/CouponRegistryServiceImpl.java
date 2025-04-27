package com.starbucks.back.coupon.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.util.RedisUtil;
import com.starbucks.back.coupon.domain.Coupon;
import com.starbucks.back.coupon.domain.CouponRegistry;
import com.starbucks.back.coupon.domain.enums.CouponState;
import com.starbucks.back.coupon.dto.in.RequestDownloadCouponDto;
import com.starbucks.back.coupon.dto.in.RequestUseCouponDto;
import com.starbucks.back.coupon.dto.out.ResponseGetMyCouponsDto;
import com.starbucks.back.coupon.infrastructure.CouponRegistryRepository;
import com.starbucks.back.coupon.infrastructure.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponRegistryServiceImpl implements CouponRegistryService {

    private final CouponRegistryRepository couponRegistryRepository;
    private final CouponRepository couponRepository;
    private final RedisUtil<String> redisUtil;

    @Transactional
    public void downloadCoupon(RequestDownloadCouponDto requestDownloadCouponDto) {
        final String key = "coupon:remaining:" + requestDownloadCouponDto.getCouponUuid();

        boolean alreadyDownloaded = couponRegistryRepository.existsByUserUuidAndCouponUuidAndDeletedFalse(
                requestDownloadCouponDto.getUserUuid(), requestDownloadCouponDto.getCouponUuid());
        if (alreadyDownloaded) {
            throw new BaseException(BaseResponseStatus.COUPON_ALREADY_DOWNLOADED);
        }

        final Long result = redisUtil.executeCouponDownloadScript(key);

        if (result == null || result == -1) {
            throw new BaseException(BaseResponseStatus.COUPON_NOT_FOUND);
        }
        if (result == 0) {
            throw new BaseException(BaseResponseStatus.COUPON_OUT_OF_STOCK);
        }

        couponRegistryRepository.save(requestDownloadCouponDto.toEntity());

        if (result == 1) {
            String remaining = redisUtil.get(key);
            if (remaining != null && remaining.equals("0")) {
                // DB에서도 쿠폰 삭제 처리
                final Coupon coupon = couponRepository.findByCouponUuidAndDeletedFalse(
                        requestDownloadCouponDto.getCouponUuid()
                );

                //redisUtil.delete(key);
                coupon.softDelete();
            }
        }
    }

    @Transactional(readOnly = true)
    public List<ResponseGetMyCouponsDto> getMyCoupons(String userUuid) {
        return couponRegistryRepository.findAllByUserUuidAndDeletedFalse(userUuid)
                .stream()
                .map(ResponseGetMyCouponsDto::from)
                .toList();
    }

    @Transactional
    public void useCoupon(RequestUseCouponDto requestUseCouponDto) {
        final CouponRegistry registry = couponRegistryRepository.findByUserUuidAndCouponUuidAndDeletedFalse
                        (requestUseCouponDto.getUserUuid(), requestUseCouponDto.getCouponUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.COUPON_NOT_FOUND));

        if (registry.getState() == CouponState.USED) {
            throw new BaseException(BaseResponseStatus.COUPON_ALREADY_USED);
        }

        couponRegistryRepository.save(requestUseCouponDto.toEntity(registry));
        registry.softDelete();
    }
}
