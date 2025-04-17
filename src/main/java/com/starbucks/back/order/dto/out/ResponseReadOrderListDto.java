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
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private Integer discountRate;

    @Builder
    public ResponseReadOrderListDto(
            String orderListUuid,
            String orderCode,
            String shippingAddressUuid,
            LocalDateTime createdAt,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            Integer discountRate
    ) {
        this.orderListUuid = orderListUuid;
        this.orderCode = orderCode;
        this.shippingAddressUuid = shippingAddressUuid;
        this.createdAt = createdAt;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.discountRate = discountRate;
    }

    // entity => vo
    public static ResponseReadOrderListDto from(OrderList orderList) {
        return ResponseReadOrderListDto.builder()
                .orderListUuid(orderList.getOrderListUuid())
                .orderCode(orderList.getOrderCode())
                .shippingAddressUuid(orderList.getShippingAddressUuid())
                .createdAt(orderList.getCreatedAt())
                .totalOriginPrice(orderList.getTotalOriginPrice())
                .totalPurchasePrice(orderList.getTotalPurchasePrice())
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
                .totalOriginPrice(totalOriginPrice)
                .totalPurchasePrice(totalPurchasePrice)
                .discountRate(discountRate)
                .build();
    }
}
