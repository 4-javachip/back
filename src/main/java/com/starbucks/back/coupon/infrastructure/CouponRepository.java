package com.starbucks.back.coupon.infrastructure;

import com.starbucks.back.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    // 사용가능한 쿠폰 조회
    List<Coupon> findAllByDeletedFalse();
    Coupon findByCouponUuidAndDeletedFalse(String couponUuid);
}
