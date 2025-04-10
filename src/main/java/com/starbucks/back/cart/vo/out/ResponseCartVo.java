package com.starbucks.back.cart.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseCartVo {

    private String userUuid;
    private String cartUuid;
    private Integer productQuantity;
    private Boolean checked;
    private String productUuid;
    private String productOptionUuid;

    @Builder
    public ResponseCartVo(
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
}
