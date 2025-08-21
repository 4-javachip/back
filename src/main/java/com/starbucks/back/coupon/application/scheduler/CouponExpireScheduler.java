//package com.starbucks.back.coupon.application.scheduler;
//
//import com.starbucks.back.coupon.application.CouponRegistryService;
//import com.starbucks.back.coupon.application.CouponService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class CouponExpireScheduler {
//    private final CouponService couponService;
//    private final CouponRegistryService couponRegistryService;
//
//    @Scheduled(cron = "0 0 0 * * *") // 매일 자정 실행
//    @Transactional
//    public void expireCoupons() {
//        LocalDate today = LocalDate.now();
//
//        log.info("[스케줄러] 만료된 쿠폰 소프트 딜리트 시작");
//
//        // 1. coupon 테이블: 만료된 쿠폰 soft delete
//        int deletedCoupons = couponRepository.softDeleteExpiredCoupons(today);
//        log.info("[스케줄러] 만료된 쿠폰 {}개 soft delete 완료", deletedCoupons);
//
//        // 2. coupon_registry 테이블: 관련 레지스트리 soft delete + 상태 변경
//        int updatedRegistries = couponRegistryRepository.expireAndDeleteRegistries(today);
//        log.info("[스케줄러] 보관함에서 만료된 쿠폰 {}개 업데이트 완료", updatedRegistries);
//    }
//}
