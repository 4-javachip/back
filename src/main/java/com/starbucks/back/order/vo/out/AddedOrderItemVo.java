package com.starbucks.back.order.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddedOrderItemVo {
    private String name;
    private Integer quantity;
    private String thumbnail;
    private String sizeName;
    private String colorName;

    @Builder
    public AddedOrderItemVo(String name, Integer quantity, String thumbnail, String sizeName, String colorName) {
        this.name = name;
        this.quantity = quantity;
        this.thumbnail = thumbnail;
        this.sizeName = sizeName;
        this.colorName = colorName;
    }
}
