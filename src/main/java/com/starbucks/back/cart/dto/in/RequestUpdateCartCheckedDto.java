package com.starbucks.back.cart.dto.in;

import com.starbucks.back.cart.domain.Cart;
import com.starbucks.back.cart.vo.in.RequestUpdateCartCheckedVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestUpdateCartCheckedDto {
    private String userUuid;
    private String cartUuid;
    private Boolean checked;

    @Builder
    public RequestUpdateCartCheckedDto(String userUuid, String cartUuid, Boolean checked) {
        this.userUuid = userUuid;
        this.cartUuid = cartUuid;
        this.checked = checked;
    }

    //update
    public Cart updateCart(Cart cart) {
        return Cart.builder()
                .id(cart.getId())
                .userUuid(userUuid)
                .cartUuid(cartUuid)
                .productQuantity(cart.getProductQuantity())
                .checked(checked)
                .productUuid(cart.getProductUuid())
                .productOptionListUuid(cart.getProductOptionListUuid())
                .build();
    }

    public static RequestUpdateCartCheckedDto from(
            String userUuid,
            RequestUpdateCartCheckedVo requestUpdateCartCheckedVo
    ) {
        return RequestUpdateCartCheckedDto.builder()
                .userUuid(userUuid)
                .cartUuid(requestUpdateCartCheckedVo.getCartUuid())
                .checked(requestUpdateCartCheckedVo.getChecked())
                .build();
    }
}
