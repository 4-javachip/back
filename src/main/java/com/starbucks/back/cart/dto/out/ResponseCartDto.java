package com.starbucks.back.cart.dto.out;

import com.starbucks.back.cart.domain.Cart;
import com.starbucks.back.cart.vo.out.ResponseCartVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseCartDto {

    private final String userUuid;
    private final String cartUuid;
    private final Integer productQuantity;
    private final Boolean checked;
    private final String productUuid;
    private final String productOptionUuid;

    @Builder
    public ResponseCartDto(
            String userUuid,
            String cartUuid,
            Integer productQuantity,
            Boolean checked,
            String productUuid,
            String productOptionUuid
    ) {
        this.userUuid = userUuid;
        this.cartUuid = cartUuid;
        this.productQuantity = productQuantity;
        this.checked = checked;
        this.productUuid = productUuid;
        this.productOptionUuid = productOptionUuid;
    }

    // entity => dto
    public static ResponseCartDto from(Cart cart) {
        return ResponseCartDto.builder()
                .userUuid(cart.getUserUuid())
                .cartUuid(cart.getCartUuid())
                .productQuantity(cart.getProductQuantity())
                .checked(cart.getChecked())
                .productUuid(cart.getProductUuid())
                .productOptionUuid(cart.getProductOptionUuid())
                .build();
    }

    // dto => vo
    public ResponseCartVo toVo() {
        return ResponseCartVo.builder()
                .cartUuid(cartUuid)
                .productQuantity(productQuantity)
                .productUuid(productUuid)
                .productOptionUuid(productOptionUuid)
                .checked(checked)
                .build();
    }
}
