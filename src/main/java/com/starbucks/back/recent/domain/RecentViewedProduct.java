package com.starbucks.back.recent.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recent_viewed_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecentViewedProduct extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 최근 본 상품 UUID
     */
    @Column(name = "recent_viewed_product_uuid", nullable = false, unique = true)
    private String recentViewedProductUuid;

    /**
     * 유저 UUID
     */
    @Column(name = "user_uuid", nullable = false)
    private String userUuid;

    /**
     * 상품 UUID
     */
    @Column(name = "product_uuid", nullable = false)
    private String productUuid;

    @Builder
    public RecentViewedProduct(Long id, String recentViewedProductUuid, String userUuid, String productUuid) {
        this.id = id;
        this.recentViewedProductUuid = recentViewedProductUuid;
        this.userUuid = userUuid;
        this.productUuid = productUuid;
    }

}
