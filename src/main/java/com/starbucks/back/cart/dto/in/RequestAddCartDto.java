package com.starbucks.back.cart.dto.in;

import com.starbucks.back.cart.domain.Cart;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestAddCartDto {
    private String userUuid;
    private Integer productQuantity;
    private Boolean checked;
    private String productOptionListUuid;

    @Builder
    public RequestAddCartDto(
            String userUuid,
            Integer productQuantity,
            Boolean checked,
            String productOptionListUuid
    ) {
        this.userUuid = userUuid;
        this.productQuantity = productQuantity;
        this.checked = checked;
        this.productOptionListUuid = productOptionListUuid;
    }

    // dto => entity
    public Cart toEntity() {
        return Cart.builder()
                .userUuid(userUuid)
                .productQuantity(productQuantity)
                .checked(checked)
                .productOptionListUuid(productOptionListUuid)
                .build();
    }
    // vo => dto
    public static RequestAddCartDto from(Cart cart) {
        return RequestAddCartDto.builder()
                .userUuid(cart.getUserUuid())
                .productQuantity(cart.getProductQuantity())
                .checked(cart.getChecked())
                .productOptionListUuid(cart.getProductOptionListUuid())
                .build();
    }
}
