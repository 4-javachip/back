package com.starbucks.back.product.domain;

import com.starbucks.back.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품 이름
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * 상품 uuid
     */
    @Column(name = "product_uuid", length = 50, nullable = false, unique = true)
    private String productUuid;

    /**
     * 베스트
     */
    @Column(name = "best", nullable = false, columnDefinition = "boolean default false")
    private boolean best;

    public boolean isNew() {
        return this.getCreatedAt() != null
                && this.getCreatedAt().isAfter(LocalDateTime.now().minusMonths(1));
    }

    @Builder
    public Product(Long id, String name, String productUuid) {
        this.id = id;
        this.name = name;
        this.productUuid = productUuid;
        this.best = false;
    }

}
