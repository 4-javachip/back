package com.starbucks.back.cart.dto.in;

import com.starbucks.back.cart.domain.Cart;
import com.starbucks.back.cart.vo.in.RequestCartVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCartDto {

    private Long id;
    private String userUuid;
    private String cartUuid;
    private Integer productQuantity;
    private Boolean checked;
    private String productOptionListUuid;

    @Builder
    public RequestCartDto(
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

    public Cart updateEntity(Cart cart) {
        return Cart.builder()
                .id(id)
                .userUuid(userUuid)
                .cartUuid(cartUuid)
                .productQuantity(productQuantity)
                .checked(checked)
                .productOptionListUuid(productOptionListUuid)
                .build();
    }

    public Cart toEntity() {
        return Cart.builder()
                .id(id)
                .userUuid(userUuid)
                .cartUuid(cartUuid)
                .productQuantity(productQuantity)
                .checked(checked)
                .productOptionListUuid(productOptionListUuid)
                .build();
    }

    public static RequestCartDto from (RequestCartVo requestCartVo) {
        return RequestCartDto.builder()
                .id(requestCartVo.getId())
                .userUuid(requestCartVo.getUserUuid())
                .cartUuid(requestCartVo.getCartUuid())
                .productQuantity(requestCartVo.getProductQuantity())
                .checked(requestCartVo.getChecked())
                .productOptionListUuid(requestCartVo.getProductOptionListUuid())
                .build();
    }
}
