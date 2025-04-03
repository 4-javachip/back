package com.starbucks.back.cart.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 유저 uuid
     */
    @Column(name = "user_uuid", nullable = false, length = 100)
    private String userUuid;

    /**
     * 카트 uuid
     */
    @Column(name = "cart_uuid", nullable = false, length = 100)
    private String cartUuid;

    /**
     * 상품 수량
     */
    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;

    /**
     * 체크 여부
     */
    @Column(name = "checked", nullable = false)
    private Boolean checked;

    /**
     * 상품 uuid
     */
    @Column(name = "product_uuid", nullable = false, length = 100)
    private String productUuid;

    /**
     * 상품 옵션 리스트 uuid
     */
    @Column(name = "product_option_list_uuid", nullable = false, length = 100)
    private String productOptionListUuid;

    @Builder
    public Cart (
            Long id,
            String userUuid,
            String cartUuid,
            Integer productQuantity,
            Boolean checked,
            String productUuid,
            String productOptionListUuid
    ) {
        this.id = id;
        this.userUuid = userUuid;
        this.cartUuid = cartUuid;
        this.productQuantity = productQuantity;
        this.checked = checked;
        this.productUuid = productUuid;
        this.productOptionListUuid = productOptionListUuid;
    }
}
