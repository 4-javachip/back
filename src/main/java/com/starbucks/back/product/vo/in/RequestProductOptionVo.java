package com.starbucks.back.product.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestProductOptionVo {

    private String productOptionUuid;
    private String productUuid;
    private Long colorOptionId;
    private Long sizeOptionId;
    private Integer stock;
    private Integer price;
    private Integer discountRate;

}
