package com.starbucks.back.coupon.application;

import com.starbucks.back.common.util.RedisUtil;
import com.starbucks.back.coupon.domain.Coupon;
import com.starbucks.back.coupon.dto.in.RequestAddCouponDto;
import com.starbucks.back.coupon.dto.out.ResponseGetAvailableCouponDto;
import com.starbucks.back.coupon.infrastructure.CouponRepository;
import com.starbucks.back.coupon.infrastructure.CouponRegistryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponRegistryRepository couponRegistryRepository;
    private final RedisUtil redisUtil;

    @Transactional(readOnly = true)
    public List<ResponseGetAvailableCouponDto> getAvailableCoupons(String userUuid) {
        List<Coupon> availableCoupons = couponRepository.findAllByDeletedFalse();
        List<String> downloadedCouponUuids = couponRegistryRepository.findCouponUuidsByUserUuid(userUuid);

        return availableCoupons.stream()
                .filter(coupon -> !downloadedCouponUuids.contains(coupon.getCouponUuid()))
                .map(ResponseGetAvailableCouponDto::from)
                .toList();
    }

    @Transactional
    public void addCoupon(RequestAddCouponDto requestAddCouponDto) {
        final Coupon coupon = requestAddCouponDto.toEntity();
        couponRepository.save(coupon);

        if (coupon.getSupply() != null) {
            String key = "coupon:remaining:" + coupon.getCouponUuid();
            long daysUntilExpiry = java.time.temporal.ChronoUnit.DAYS.between(
                    java.time.LocalDate.now(), coupon.getValidUntil()
            );

            // 0 이하일 경우 그냥 하루라도 살아있게 처리
            daysUntilExpiry = Math.max(daysUntilExpiry, 1);

            redisUtil.set(key, coupon.getSupply(), daysUntilExpiry, TimeUnit.DAYS);
        }
    }
}
