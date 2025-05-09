package com.starbucks.back.product.domain;

import com.starbucks.back.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_description")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDescription extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품 uuid
     */
    @Column(name = "product_uuid", length = 50, nullable = false, unique = true)
    private String productUuid;

    /**
     * 상품 간단 설명
     */
    @Column(name = "description")
    private String description;

    /**
     * 상품 상세 설명
     */
    @Lob
    @Column(name = "detail_description", columnDefinition = "TEXT", nullable = false)
    private String detailDescription;

    @Builder
    public ProductDescription(Long id, String productUuid, String description, String detailDescription) {
        this.id = id;
        this.productUuid = productUuid;
        this.description = description;
        this.detailDescription = detailDescription;
    }

}
