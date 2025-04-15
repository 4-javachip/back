package com.starbucks.back.recent.infrastructure;

import com.starbucks.back.common.util.DateTimeUtil;
import com.starbucks.back.common.util.RedisUtil;
import com.starbucks.back.recent.domain.RecentViewedProduct;
import com.starbucks.back.recent.dto.in.RequestAddRecentViewedProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

import static java.util.UUID.randomUUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecentViewedProductMigrationScheduler {

    private final RedisUtil<String> redisUtil;
    private final RecentViewedProductRepository recentViewedProductRepository;
    private final DateTimeUtil dateTimeUtil;

    // 7일 초단위 (7 * 24 * 60 * 60)
    private static final long SEVEN_DAYS_SECONDS = 7 * 24 * 60 * 60;

    /**
     * 1시간마다 실행: 각 사용자별 Redis List("recent-viewed:{userUuid}")의 항목 중,
     * 열람 시각(epoch)이 만료 기준(현재 epoch - 7일)보다 작은 항목을 MySQL로 마이그레이션 후 리스트에서 삭제
     */
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void migrateExpiredProducts() throws Exception {
        final long nowEpoch = Instant.now().getEpochSecond();
        final long expirationThreshold = nowEpoch - SEVEN_DAYS_SECONDS;
        log.info("스케줄러 실행: 현재 epoch = {}, 만료 기준 epoch = {}", nowEpoch, expirationThreshold);

        Set<String> keys = redisUtil.getKeys("recent-viewed:*");
        if (keys == null || keys.isEmpty()) {
            log.info("스케줄러: 처리할 데이터 없음");
            return;
        }

        keys.forEach(key -> processExpiredItemsForKey(key, expirationThreshold));
    }

    private void processExpiredItemsForKey(String redisKey, long expirationThreshold) {
        List<String> items = redisUtil.getListRange(redisKey, 0, -1);
        if (items == null || items.isEmpty()) return;

        final String userUuid = redisKey.replace("recent-viewed:", "");

        for (String item : items) {
            try {
                String[] parts = item.split(":");
                String productUuid = parts[0];
                long viewedAtEpoch = Long.parseLong(parts[1]);

                // 열람 시각이 만료 기준보다 작으면 MySQL로 마이그레이션
                if (viewedAtEpoch < expirationThreshold) {
                    recentViewedProductRepository.save(
                            RequestAddRecentViewedProductDto.of(userUuid, productUuid)
                                    .toEntity(dateTimeUtil.convertEpochToLocalDateTime(viewedAtEpoch))
                    );
                    log.info("스케줄러: 사용자 {}의 상품 {} (열람 시각: {}) 마이그레이션 완료", userUuid, productUuid, viewedAtEpoch);

                    redisUtil.removeFromList(redisKey, 1, item);
                }
            } catch (Exception e) {
                log.error("스케줄러: 데이터 처리 중 예외 발생: {}", e.getMessage());
            }
        }

        // 리스트가 비어있으면 Redis에서 삭제(Redis 내부 구현에 따라 삭제 안될 수도 있으므로 명시적으로 삭제해줌)
        List<String> remainingItems = redisUtil.getListRange(redisKey, 0, -1);
        if (remainingItems == null || remainingItems.isEmpty()) {
            redisUtil.delete(redisKey);
            log.info("스케줄러: Redis 키 [{}]가 빈 리스트이므로 삭제 처리됨", redisKey);
        }
    }

}