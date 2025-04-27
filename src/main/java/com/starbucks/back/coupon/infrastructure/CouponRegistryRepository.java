package com.starbucks.back.coupon.infrastructure;

import com.starbucks.back.coupon.domain.CouponRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CouponRegistryRepository extends JpaRepository<CouponRegistry, Long> {
    // 유저가 다운로드한 쿠폰 리스트 조회
    List<CouponRegistry> findAllByUserUuidAndDeletedFalse(String userUuid);

    @Query("SELECT cr.couponUuid FROM CouponRegistry cr WHERE cr.userUuid = :userUuid")
    List<String> findCouponUuidsByUserUuid(String userUuid);
    boolean existsByUserUuidAndCouponUuidAndDeletedFalse(String userUuid, String couponUuid);
    Optional<CouponRegistry> findByUserUuidAndCouponUuidAndDeletedFalse(String userUuid, String couponUuid);
}
