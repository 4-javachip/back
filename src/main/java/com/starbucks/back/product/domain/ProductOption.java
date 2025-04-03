package com.starbucks.back.product.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOption extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품 옵션 리스트 uuid
     */
    @Column(name = "product_option_uuid", length = 50, nullable = false, unique = true)
    private String productOptionUuid;

    /**
     * 상품 uuid
     */
    @Column(name = "product_uuid", length = 50, nullable = false)
    private String productUuid;

    /**
     * 색상 옵션 id
     */
    @Column(name = "color_option_id")
    private Long colorOptionId;

    /**
     * 사이즈 옵션 id
     */
    @Column(name = "size_option_id")
    private Long sizeOptionId;

    /**
     * 재고
     */
    @Column(name = "stock", nullable = false)
    private Integer stock;

    /**
     * 가격
     */
    @Column(name = "price", nullable = false)
    private Integer price;

    /**
     * 할인율
     */
    @Column(name = "discount_rate")
    private Integer discountRate;

    /**
     * 최종 가격
     */
    @Column(name = "total_price")
    private Integer totalPrice;

    @Builder
    public ProductOption(Long id, String productOptionUuid, String productUuid,
                         Long colorOptionId, Long sizeOptionId, Integer stock, Integer price,
                         Integer discountRate, Integer totalPrice) {
        this.id = id;
        this.productOptionUuid = productOptionUuid;
        this.productUuid = productUuid;
        this.colorOptionId = colorOptionId;
        this.sizeOptionId = sizeOptionId;
        this.stock = stock;
        this.price = price;
        this.discountRate = discountRate;
        this.totalPrice = totalPrice;
    }

}
