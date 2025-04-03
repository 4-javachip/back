package com.starbucks.back.product.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseProductOptionVo {

    private String productOptionUuid;
    private String productUuid;
    private Long colorOptionId;
    private Long sizeOptionId;
    private Integer stock;
    private Integer price;
    private Integer discountRate;
    private Integer totalPrice;

    @Builder
    public ResponseProductOptionVo(String productOptionUuid, String productUuid,
                                   Long colorOptionId, Long sizeOptionId, Integer stock, Integer price,
                                   Integer discountRate, Integer totalPrice) {
        this.productOptionUuid = productOptionUuid;
        this.productUuid = productUuid;
        this.colorOptionId = colorOptionId;
        this.sizeOptionId = sizeOptionId;
        this.stock = stock;
        this.price = price;
        this.discountRate = discountRate;
        this.totalPrice = totalPrice;
    }

}
