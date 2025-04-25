package com.starbucks.back.coupon.infrastructure;

import com.starbucks.back.coupon.domain.CouponRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRegistryRepository extends JpaRepository<CouponRegistry, Long> {
}
