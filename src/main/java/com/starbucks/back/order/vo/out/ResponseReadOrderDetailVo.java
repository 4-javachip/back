package com.starbucks.back.order.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadOrderDetailVo {

    private String orderListUuid;
    private String orderDetailUuid;
    private String productUuid;
    private String productOptionUuid;
    private String name;
    private String thumbnail;
    private Integer price;
    private Integer discountRate;
    private Integer totalPrice;
    private Integer quantity;
    private String sizeName;
    private String colorName;

    @Builder
    public ResponseReadOrderDetailVo(
            String orderListUuid,
            String orderDetailUuid,
            String productUuid,
            String productOptionUuid,
            String name,
            String thumbnail,
            Integer price,
            Integer discountRate,
            Integer totalPrice,
            Integer quantity,
            String sizeName,
            String colorName
    ) {
        this.orderListUuid = orderListUuid;
        this.orderDetailUuid = orderDetailUuid;
        this.productUuid = productUuid;
        this.productOptionUuid = productOptionUuid;
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
        this.discountRate = discountRate;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.sizeName = sizeName;
        this.colorName = colorName;
    }
}
