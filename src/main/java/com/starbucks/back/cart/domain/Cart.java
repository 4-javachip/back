package com.starbucks.back.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 유저 uuid
     */
    @Column(name = "user_uuid", nullable = false, length = 50)
    private String userUuid;

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
     * 상품 옵션 리스트 uuid
     */
    @Column(name = "product_option_list_uuid", nullable = false, length = 50)
    private String productOptionListUuid;

    @Builder
    public Cart(
            Long id,
            String userUuid,
            Integer productQuantity,
            Boolean checked,
            String productOptionListUuid
    ) {
        this.id = id;
        this.userUuid = userUuid;
        this.productQuantity = productQuantity;
        this.checked = checked;
        this.productOptionListUuid = productOptionListUuid;
    }
}
