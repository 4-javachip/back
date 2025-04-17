package com.starbucks.back.order.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseReadOrderListVo {
    private String orderListUuid;
    private String orderCode;
    private String shippingAddressUuid;
    private LocalDateTime createdAt;
    private Integer totalOrderPrice;
    private Integer totalAmount;
    private Integer discountRate;

    @Builder
    public ResponseReadOrderListVo(
            String orderListUuid,
            String orderCode,
            String shippingAddressUuid,
            LocalDateTime createdAt,
            Integer totalOrderPrice,
            Integer totalAmount,
            Integer discountRate
    ) {
        this.orderListUuid = orderListUuid;
        this.orderCode = orderCode;
        this.shippingAddressUuid = shippingAddressUuid;
        this.createdAt = createdAt;
        this.totalOrderPrice = totalOrderPrice;
        this.totalAmount = totalAmount;
        this.discountRate = discountRate;
    }
}
