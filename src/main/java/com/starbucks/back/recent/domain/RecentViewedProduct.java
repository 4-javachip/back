package com.starbucks.back.recent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starbucks.back.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "recent_viewed_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecentViewedProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "recent_viewed_product_uuid", nullable = false, unique = true, length = 40, updatable = false)
    private String recentViewedProductUuid;

    @Column(name = "user_uuid", nullable = false, length = 40, updatable = false)
    private String userUuid;

    @Column(name = "product_uuid", nullable = false, length = 40, updatable = false)
    private String productUuid;

    /**
     * 상품을 열람한 시간 (redis에 저장된 시간)
     */
    @Column(name = "viewed_at", nullable = false, columnDefinition = "DATETIME(0)")
    private LocalDateTime viewedAt;

    /**
     * 최근 본 상품에서 사라진 시간 (redis -> mysql)
     */
    @Column(name = "expired_at", nullable = false, columnDefinition = "DATETIME(0)")
    private LocalDateTime expiredAt;

    @Builder
    public RecentViewedProduct(String recentViewedProductUuid, String userUuid, String productUuid, LocalDateTime viewedAt, LocalDateTime expiredAt) {
        this.recentViewedProductUuid = recentViewedProductUuid;
        this.userUuid = userUuid;
        this.productUuid = productUuid;
        this.viewedAt = viewedAt;
        this.expiredAt = expiredAt;
    }

}
