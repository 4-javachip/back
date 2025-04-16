package com.starbucks.back.recent.application;

import com.starbucks.back.common.util.DateTimeUtil;
import com.starbucks.back.common.util.RedisUtil;
import com.starbucks.back.recent.dto.in.RequestAddRecentViewedProductDto;
import com.starbucks.back.recent.dto.out.ResponseGetRecentViewedProductDto;
import com.starbucks.back.recent.infrastructure.RecentViewedProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecentViewedProductServiceImpl implements RecentViewedProductService {

    private final RedisUtil<String> redisUtil;
    private final RecentViewedProductRepository recentViewedProductRepository;
    private final DateTimeUtil dateTimeUtil;

    // 최근 본 상품 최대 개수
    private final int MAX_COUNT = 10;

    @Override
    public void addRecentViewedProduct(RequestAddRecentViewedProductDto requestAddRecentViewedProductDto) throws Exception {
        final String redisKey = "recent-viewed:" + requestAddRecentViewedProductDto.getUserUuid();

        // 재열람 처리: 기존에 동일 상품이 존재하면 모두 제거
        refreshExistingRecentView(redisKey, requestAddRecentViewedProductDto.getProductUuid());

        // 저장할 값 형식: "productUuid:현재 epoch"
        final String valueToPush =
                requestAddRecentViewedProductDto.getProductUuid() + ":" + Instant.now().getEpochSecond();
        redisUtil.leftPush(redisKey, valueToPush);
        log.info("Redis [{}]에 값 {} LPUSH", redisKey, valueToPush);

        // 리스트 길이가 MAX_COUNT 초과 시 초과 데이터 처리 (Mysql로 이동 후 LTRIM)
        enforceMaxCount(redisKey, requestAddRecentViewedProductDto.getUserUuid());
    }

    @Override
    public List<ResponseGetRecentViewedProductDto> getRecentViewedProducts(String userUuid) throws Exception {
        final List<String> items = redisUtil.getListRange(
                "recent-viewed:" + userUuid, 0, MAX_COUNT - 1
        );
        // Redis에서 조회한 리스트가 null이면 빈 ArrayList로 대체
        final List<String> finalItems = (items == null) ? new ArrayList<>() : items;

        // 인덱스와 상품 uuid를 DTO로 변환
        return IntStream.range(0, finalItems.size())
                .mapToObj(i -> {
                    // 값인 "productUuid:epoch"에서 productUuid만 추출
                    return ResponseGetRecentViewedProductDto.of(i, finalItems.get(i).split(":")[0]);
                })
                .toList();
    }


    // 중복된 상품이 있는 경우 해당 항목 제거 후 재열람 처리
    private void refreshExistingRecentView(String redisKey, String productUuid) {
        List<String> items = redisUtil.getListRange(redisKey, 0, -1);
        if (items != null) {
            for (String item : items) {
                String[] parts = item.split(":");
                if (parts.length >= 1 && parts[0].equals(productUuid)) {
                    redisUtil.removeFromList(redisKey, 0, item);
                    log.info("Redis [{}]에서 기존 상품 {} 제거 (재열람 갱신)", redisKey, productUuid);
                }
            }
        }
    }

    // 최대 개수 초과 시, 리스트의 인덱스 MAX_COUNT 이상 항목을 MySQL로 이동 후, 리스트를 LTRIM으로 길이 제한
    private void enforceMaxCount(String redisKey, String userUuid) throws Exception {
        List<String> excessItems = redisUtil.getListRange(redisKey, MAX_COUNT, -1);

        if (excessItems != null && !excessItems.isEmpty()) {
            excessItems.forEach(item -> {
                String[] parts = item.split(":");
                String productUuid = parts[0];
                long viewedAtEpoch = Long.parseLong(parts[1]);

                recentViewedProductRepository.save(
                        RequestAddRecentViewedProductDto.of(userUuid, productUuid)
                        .toEntity(dateTimeUtil.convertEpochToLocalDateTime(viewedAtEpoch))
                );
                log.info("MySQL에 사용자 {}의 상품 {} (열람 시각: {}) 기록 저장 (MAX_COUNT 초과)", userUuid, productUuid, viewedAtEpoch);
            });
            // LTRIM으로 리스트를 0~MAX_COUNT-1 범위외의 항목 제거
            redisUtil.trimList(redisKey, 0, MAX_COUNT - 1);
        }
    }
}
