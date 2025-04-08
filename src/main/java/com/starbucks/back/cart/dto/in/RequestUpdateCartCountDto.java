package com.starbucks.back.cart.dto.in;

import com.starbucks.back.cart.domain.Cart;
import com.starbucks.back.cart.vo.in.RequestUpdateCartCountVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestUpdateCartCountDto {

    private String userUuid;
    private String cartUuid;
    private Integer productQuantity;

    @Builder
    public RequestUpdateCartCountDto(
            String userUuid,
            String cartUuid,
            Integer productQuantity
    ) {
        this.userUuid = userUuid;
        this.cartUuid = cartUuid;
        this.productQuantity = productQuantity;
    }

    //update
    public Cart updateCart(Cart cart) {
        return Cart.builder()
                .id(cart.getId())
                .userUuid(cart.getUserUuid())
                .cartUuid(cartUuid)
                .productQuantity(productQuantity)
                .checked(cart.getChecked())
                .productUuid(cart.getProductUuid())
                .productOptionListUuid(cart.getProductOptionListUuid())
                .build();
    }

    public static RequestUpdateCartCountDto from(
            String userUuid,
            RequestUpdateCartCountVo requestUpdateCartCountVo
    ) {
        return RequestUpdateCartCountDto.builder()
                .userUuid(userUuid)
                .cartUuid(requestUpdateCartCountVo.getCartUuid())
                .productQuantity(requestUpdateCartCountVo.getProductQuantity())
                .build();
    }
}
