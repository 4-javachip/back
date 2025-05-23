package com.starbucks.back.product.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_category_list")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategoryList extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품 uuid
     */
    @Column(name = "product_uuid", length = 50, nullable = false, unique = true)
    private String productUuid;

    /**
     * 카테고리 id
     */
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    /**
     * 서브 카테고리 id
     */
    @Column(name = "sub_category_id")
    private Long subCategoryId;

    @Builder
    public ProductCategoryList(Long id, String productUuid, Long categoryId, Long subCategoryId) {
        this.id = id;
        this.productUuid = productUuid;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
    }

}
