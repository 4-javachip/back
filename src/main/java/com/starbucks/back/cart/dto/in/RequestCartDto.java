package com.starbucks.back.cart.dto.in;

import com.starbucks.back.cart.application.CartService;
import com.starbucks.back.cart.domain.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCartDto {

    private Long id;
    private String userUuid;
    private Integer productQuantity;
    private Boolean checked;
    private String productOptionListUuid;

    @Builder
    public RequestCartDto(
            Long id,
            String userUuid,
            Integer productQuantity,
            Boolean checked,
            String productOptionListUuid
    ) {
        this.id = id;
        this.userUuid = userUuid;
        this.productQuantity = productQuantity;
        this.checked = checked;
        this.productOptionListUuid = productOptionListUuid;
    }

    // dto => entity
    public Cart toEntity() {
        return Cart.builder()
                .id(id)
                .userUuid(userUuid)
                .productQuantity(productQuantity)
                .checked(checked)
                .productOptionListUuid(productOptionListUuid)
                .build();
    }

    // vo => dto
    public static RequestCartDto from(Cart cart) {
        return RequestCartDto.builder()
                .id(cart.getId())
                .userUuid(cart.getUserUuid())
                .productQuantity(cart.getProductQuantity())
                .checked(cart.getChecked())
                .productOptionListUuid(cart.getProductOptionListUuid())
                .build();
    }
}
