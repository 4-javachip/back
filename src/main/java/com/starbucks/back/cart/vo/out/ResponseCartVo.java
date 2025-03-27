package com.starbucks.back.cart.vo.out;

import com.starbucks.back.cart.domain.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseCartVo {

    private Long id;
    private String userUuid;
    private String cartUuid;
    private Integer productQuantity;
    private Boolean checked;
    private String productOptionListUuid;

    @Builder
    public ResponseCartVo(
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

    public static ResponseCartVo from (Cart cart) {
        return ResponseCartVo.builder()
                .id(cart.getId())
                .userUuid(cart.getUserUuid())
                .cartUuid(cart.getCartUuid())
                .productQuantity(cart.getProductQuantity())
                .checked(cart.getChecked())
                .productOptionListUuid(cart.getProductOptionListUuid())
                .build();
    }

    public ResponseCartVo toVo() {
        return ResponseCartVo.builder()
                .id(id)
                .userUuid(userUuid)
                .cartUuid(cartUuid)
                .productQuantity(productQuantity)
                .checked(checked)
                .productOptionListUuid(productOptionListUuid)
                .build();
    }
}
