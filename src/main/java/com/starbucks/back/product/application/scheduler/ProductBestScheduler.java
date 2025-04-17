package com.starbucks.back.product.application.scheduler;

import com.starbucks.back.product.application.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductBestScheduler {

    private final ProductService productService;

    /**
     * 매일 새벽 3시에 베스트 상품 갱신
     */
    @Scheduled(cron = "0 0 3 * * *") // 새벽 3시 실행
    public void updateBestProducts() {
        log.info("🔄 베스트 상품 업데이트 스케줄러 시작");
        productService.updateBestProductStatus();
        log.info("✅ 베스트 상품 업데이트 완료");
    }
}

