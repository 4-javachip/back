package com.starbucks.back.order.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderDetailInfoDto {

    private String orderListUuid;
    private String name;
    private String thumbnail;
    private String price;
    private String discountRate;
    private String discountPrice;
    private String quantity;

    @Builder
    public OrderDetailInfoDto(String orderListUuid, String name, String thumbnail, String price, String discountRate, String discountPrice, String quantity) {
        this.orderListUuid = orderListUuid;
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
        this.discountRate = discountRate;
        this.discountPrice = discountPrice;
        this.quantity = quantity;
    }
}
