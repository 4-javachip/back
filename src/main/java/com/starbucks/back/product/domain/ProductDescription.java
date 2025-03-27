package com.starbucks.back.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_description")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품 옵션 uuid
     */
    @Column(name = "product_option_uuid", length = 50, nullable = false)
    private String productOptionUuid;

    /**
     * 상품 간단 설명
     */
    @Column(name = "description")
    private String description;

    /**
     * 상품 상세 설명
     */
    @Column(name = "detail_description", nullable = false)
    private String detailDescription;

    @Builder
    public ProductDescription(Long id, String productOptionUuid, String description, String detailDescription) {
        this.id = id;
        this.productOptionUuid = productOptionUuid;
        this.description = description;
        this.detailDescription = detailDescription;
    }

}
