package com.starbucks.back.recent.dto.in;

import com.starbucks.back.recent.domain.RecentViewedProduct;
import com.starbucks.back.recent.vo.in.RequestAddRecentViewedProductVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static java.util.UUID.randomUUID;

@Getter
public class RequestAddRecentViewedProductDto {
    private String userUuid;
    private String productUuid;

    @Builder
    public RequestAddRecentViewedProductDto(String userUuid, String productUuid) {
        this.userUuid = userUuid;
        this.productUuid = productUuid;
    }

    public static RequestAddRecentViewedProductDto of(String userUuid, String productUuid) {
        return RequestAddRecentViewedProductDto.builder()
                .userUuid(userUuid)
                .productUuid(productUuid)
                .build();
    }

    public RecentViewedProduct toEntity(LocalDateTime viewedAt) {
        return RecentViewedProduct.builder()
                .recentViewedProductUuid(randomUUID().toString())
                .userUuid(userUuid)
                .productUuid(productUuid)
                .viewedAt(viewedAt)
                .expiredAt(java.time.LocalDateTime.now())
                .build();
    }
}
