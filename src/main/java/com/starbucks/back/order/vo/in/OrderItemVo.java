package com.starbucks.back.order.vo.in;

import lombok.Getter;

@Getter
public class OrderItemVo {

    private String productUuid;
    private String productOptionUuid;
    private Integer quantity;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;

}
