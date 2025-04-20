package com.starbucks.back.order.vo.in;

import lombok.Getter;

import java.util.List;

@Getter
public class RequestAddOrderListVo {
    private Boolean fromCart;
    private List<OrderItemVo> orderItems;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private String shippingAddressUuid;
    private String paymentUuid;
}
