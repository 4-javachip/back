package com.starbucks.back.cart.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseCartVo {

    private String cartUuid;
    private Integer productQuantity;
    private String productUuid;
    private String productOptionUuid;

    @Builder
    public ResponseCartVo(
            String cartUuid,
            Integer productQuantity,
            String productUuid,
            String productOptionUuid
    ) {
        this.cartUuid = cartUuid;
        this.productQuantity = productQuantity;
        this.productUuid = productUuid;
        this.productOptionUuid = productOptionUuid;
    }
}
