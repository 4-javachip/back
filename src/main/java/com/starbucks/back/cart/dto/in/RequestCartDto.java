package com.starbucks.back.cart.dto.in;

import com.starbucks.back.cart.domain.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCartDto {

    private String userUuid;
    private Integer productQuantity;
    private Boolean checked;
    private String productUuid;
    private String productOptionUuid;

    @Builder
    public RequestCartDto(
            String userUuid,
            Integer productQuantity,
            Boolean checked,
            String productUuid,
            String productOptionUuid
    ) {
        this.userUuid = userUuid;
        this.productQuantity = productQuantity;
        this.checked = checked;
        this.productUuid = productUuid;
        this.productOptionUuid = productOptionUuid;
    }

    // dto => entity
    public Cart toEntity() {
        return Cart.builder()
                .userUuid(userUuid)
                .productQuantity(productQuantity)
                .checked(checked)
                .productUuid(productUuid)
                .productOptionUuid(productOptionUuid)
                .build();
    }

    // vo => dto
    public static RequestCartDto from(Cart cart) {
        return RequestCartDto.builder()
                .userUuid(cart.getUserUuid())
                .productQuantity(cart.getProductQuantity())
                .checked(cart.getChecked())
                .productUuid(cart.getProductUuid())
                .productOptionUuid(cart.getProductOptionUuid())
                .build();
    }
}
