package com.starbucks.back.order.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadOrderDetailVo {

    private String orderListUuid;
    private String orderDetailUuid;
    private String name;
    private String thumbnail;
    private Integer price;
    private Integer discountRate;
    private Integer discountPrice;
    private Integer quantity;

    @Builder
    public ResponseReadOrderDetailVo(
            String orderListUuid,
            String orderDetailUuid,
            String name,
            String thumbnail,
            Integer price,
            Integer discountRate,
            Integer discountPrice,
            Integer quantity
    ) {
        this.orderListUuid = orderListUuid;
        this.orderDetailUuid = orderDetailUuid;
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
        this.discountRate = discountRate;
        this.discountPrice = discountPrice;
        this.quantity = quantity;
    }
}
