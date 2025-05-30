package com.starbucks.back.cart.dto.in;

import com.starbucks.back.cart.domain.Cart;
import com.starbucks.back.cart.vo.in.RequestAddCartVo;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RequestAddCartDto {
    private String userUuid;
    private String cartUuid;
    private Integer productQuantity;
    private Boolean checked;
    private String productUuid;
    private String productOptionUuid;

    @Builder
    public RequestAddCartDto(
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

    // dto => entity
    public Cart toEntity() {
        return Cart.builder()
                .userUuid(userUuid)
                .cartUuid(UUID.randomUUID().toString())
                .productQuantity(productQuantity)
                .checked(checked)
                .productUuid(productUuid)
                .productOptionUuid(productOptionUuid)
                .build();
    }
    // vo => dto
    public static RequestAddCartDto from(String userUuid, RequestAddCartVo requestAddCartVo) {
        return RequestAddCartDto.builder()
                .userUuid(userUuid)
                .productQuantity(requestAddCartVo.getProductQuantity())
                .checked(requestAddCartVo.getChecked())
                .productUuid(requestAddCartVo.getProductUuid())
                .productOptionUuid(requestAddCartVo.getProductOptionUuid())
                .build();
    }
}
