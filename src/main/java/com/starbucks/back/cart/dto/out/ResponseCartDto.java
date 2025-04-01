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
    private final String productOptionListUuid;

    @Builder
    public ResponseCartDto(
            String userUuid,
            String cartUuid,
            Integer productQuantity,
            Boolean checked,
            String productOptionListUuid
    ) {
        this.userUuid = userUuid;
        this.cartUuid = cartUuid;
        this.productQuantity = productQuantity;
        this.checked = checked;
        this.productOptionListUuid = productOptionListUuid;
    }

    // entity => dto
    public static ResponseCartDto from(Cart cart) {
        return ResponseCartDto.builder()
                .userUuid(cart.getUserUuid())
                .cartUuid(cart.getCartUuid())
                .productQuantity(cart.getProductQuantity())
                .checked(cart.getChecked())
                .productOptionListUuid(cart.getProductOptionListUuid())
                .build();
    }

    // dto => vo
    public ResponseCartVo toVo() {
        return ResponseCartVo.builder()
                .userUuid(userUuid)
                .cartUuid(cartUuid)
                .productQuantity(productQuantity)
                .checked(checked)
                .productOptionListUuid(productOptionListUuid)
                .build();
    }
}
