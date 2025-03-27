package com.starbucks.back.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품 이름
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 상품 uuid
     */
    @Column(name = "product_uuid", length = 50, nullable = false, unique = true)
    private String productUuid;

    @Builder
    public Product(Long id, String name, String productUuid) {
        this.id = id;
        this.name = name;
        this.productUuid = productUuid;
    }

}
