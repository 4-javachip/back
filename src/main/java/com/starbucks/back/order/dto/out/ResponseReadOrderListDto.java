package com.starbucks.back.order.dto.out;

import com.starbucks.back.order.domain.OrderList;
import com.starbucks.back.order.vo.out.ResponseReadOrderListVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseReadOrderListDto {
    private String orderListUuid;
    private String orderCode;
    private String shippingAddressUuid;
    private LocalDateTime createdAt;
    private Integer totalOrderPrice;
    private Integer totalAmount;
    private Integer discountRate;

    @Builder
    public ResponseReadOrderListDto(
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
    };

    // entity => vo
    public static ResponseReadOrderListDto from(OrderList orderList) {
        return ResponseReadOrderListDto.builder()
                .orderListUuid(orderList.getOrderListUuid())
                .orderCode(orderList.getOrderCode())
                .shippingAddressUuid(orderList.getShippingAddressUuid())
                .createdAt(orderList.getCreatedAt())
                .totalOrderPrice(orderList.getTotalOrderPrice())
                .totalAmount(orderList.getTotalAmount())
                .discountRate(orderList.getDiscountRate())
                .build();
    }

    // dto => vo
    public ResponseReadOrderListVo toVo() {
        return ResponseReadOrderListVo.builder()
                .orderListUuid(orderListUuid)
                .orderCode(orderCode)
                .shippingAddressUuid(shippingAddressUuid)
                .createdAt(createdAt)
                .totalOrderPrice(totalOrderPrice)
                .totalAmount(totalAmount)
                .discountRate(discountRate)
                .build();
    }
}
