package com.starbucks.back.cart.dto.in;

import com.starbucks.back.cart.domain.Cart;
import com.starbucks.back.cart.vo.in.RequestUpdateCartCheckedVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestUpdateCartCheckedDto {
    private String cartUuid;
    private Boolean checked;

    @Builder
    public RequestUpdateCartCheckedDto(String cartUuid, Boolean checked) {
        this.cartUuid = cartUuid;
        this.checked = checked;
    }

    //update
    public Cart updateCart(Cart cart) {
        return Cart.builder()
                .id(cart.getId())
                .userUuid(cart.getUserUuid())
                .cartUuid(cartUuid)
                .productQuantity(cart.getProductQuantity())
                .checked(checked)
                .productOptionListUuid(cart.getProductOptionListUuid())
                .build();
    }

    public static RequestUpdateCartCheckedDto from(RequestUpdateCartCheckedVo requestUpdateCartCheckedVo) {
        return RequestUpdateCartCheckedDto.builder()
                .cartUuid(requestUpdateCartCheckedVo.getCartUuid())
                .checked(requestUpdateCartCheckedVo.getChecked())
                .build();
    }
}
