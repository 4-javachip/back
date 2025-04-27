package com.starbucks.back.best.domain;

import com.starbucks.back.common.entity.BaseEntity;
import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "best")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Best extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품 uuid
     */
    @Column(name = "product_uuid", length = 50, nullable = false, unique = true)
    private String productUuid;

    /**
     * 판매량
     */
    @Column(name = "product_sales_count", nullable = false)
    private Integer productSalesCount;

    @Builder
    public Best(Long id, String productUuid, Integer productSalesCount) {
        this.id = id;
        this.productUuid = productUuid;
        this.productSalesCount = productSalesCount;
    }

    /**
     * 판매량 증가
     * @param productSalesCount
     */
    public void increaseSalesCount(int productSalesCount) {
        this.productSalesCount += productSalesCount;
    }

}
