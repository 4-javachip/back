package com.starbucks.back.order.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadOrderDetailVo {

    private String orderListUuid;
    private String orderDetailUuid;
    private String productUuid;
    private String name;
    private String thumbnail;
    private Integer totalOriginPrice;
    private Integer discountRate;
    private Integer totalPurchasePrice;
    private Integer quantity;
    private String sizeName;
    private String colorName;

    @Builder
    public ResponseReadOrderDetailVo(
            String orderListUuid,
            String orderDetailUuid,
            String productUuid,
            String name,
            String thumbnail,
            Integer totalOriginPrice,
            Integer discountRate,
            Integer totalPurchasePrice,
            Integer quantity,
            String sizeName,
            String colorName
    ) {
        this.orderListUuid = orderListUuid;
        this.orderDetailUuid = orderDetailUuid;
        this.productUuid = productUuid;
        this.name = name;
        this.thumbnail = thumbnail;
        this.totalOriginPrice = totalOriginPrice;
        this.discountRate = discountRate;
        this.totalPurchasePrice = totalPurchasePrice;
        this.quantity = quantity;
        this.sizeName = sizeName;
        this.colorName = colorName;
    }
}
