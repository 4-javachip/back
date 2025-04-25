package com.starbucks.back.coupon.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.coupon.domain.Coupon;
import com.starbucks.back.coupon.dto.in.RequestRegisterCouponDto;
import com.starbucks.back.coupon.infrastructure.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public List<Coupon> getAllCoupon() {
        return couponRepository.findAllByDeletedFalse().orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_AVAILABLE_COUPON)
        );
    }


}
