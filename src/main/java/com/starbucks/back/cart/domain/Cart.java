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

    @Column(name = "user_uuid", nullable = false, length = 50)
    private String userUuid;

    @Column(name = "cart_uuid", nullable = false, length = 50)
    private String cartUuid;

    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;

    @Column(name = "checked", nullable = false)
    private Boolean checked;

    @Column(name = "product_option_list_uuid", nullable = false, length = 50)
    private String productOptionListUuid;

    @Builder
    public Cart(
            Long id,
            String userUuid,
            String cartUuid,
            Integer productQuantity,
            Boolean checked,
            String productOptionListUuid
    ) {
        this.id = id;
        this.userUuid = userUuid;
        this.cartUuid = cartUuid;
        this.productQuantity = productQuantity;
        this.checked = checked;
        this.productOptionListUuid = productOptionListUuid;
    }
}
