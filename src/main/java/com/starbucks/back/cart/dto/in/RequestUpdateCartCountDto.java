package com.starbucks.back.cart.dto.in;

import com.starbucks.back.cart.domain.Cart;
import com.starbucks.back.cart.vo.in.RequestUpdateCartCountVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestUpdateCartCountDto {

    private String cartUuid;
    private Integer productQuantity;

    @Builder
    public RequestUpdateCartCountDto(
            String cartUuid,
            Integer productQuantity
    ) {
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
                .productOptionListUuid(cart.getProductOptionListUuid())
                .build();
    }

    public static RequestUpdateCartCountDto from(RequestUpdateCartCountVo requestUpdateCartCountVo) {
        return RequestUpdateCartCountDto.builder()
                .cartUuid(requestUpdateCartCountVo.getCartUuid())
                .productQuantity(requestUpdateCartCountVo.getProductQuantity())
                .build();
    }
}
