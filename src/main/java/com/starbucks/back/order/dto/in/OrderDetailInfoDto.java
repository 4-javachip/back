package com.starbucks.back.order.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderDetailInfoDto {

    private String orderListUuid;
    private String name;
    private String thumbnail;
    private String totalOriginPrice;
    private String discountRate;
    private String totalPurchasePrice;
    private String quantity;

    @Builder
    public OrderDetailInfoDto(
            String orderListUuid,
            String name,
            String thumbnail,
            String totalOriginPrice,
            String discountRate,
            String totalPurchasePrice,
            String quantity
    ) {
        this.orderListUuid = orderListUuid;
        this.name = name;
        this.thumbnail = thumbnail;
        this.totalOriginPrice = totalOriginPrice;
        this.discountRate = discountRate;
        this.totalPurchasePrice = totalPurchasePrice;
        this.quantity = quantity;
    }
}
