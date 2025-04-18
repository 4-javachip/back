package com.starbucks.back.order.dto.in;

import com.starbucks.back.order.vo.in.OrderItemVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderItemDto {
    private String orderListUuid;
    private String productUuid;
    private String productOptionUuid;
    private Integer quantity;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;

    @Builder
    public OrderItemDto(
            String orderListUuid,
            String productUuid,
            String productOptionUuid,
            Integer quantity,
            Integer totalOriginPrice,
            Integer totalPurchasePrice
    ) {
        this.orderListUuid = orderListUuid;
        this.productUuid = productUuid;
        this.productOptionUuid = productOptionUuid;
        this.quantity = quantity;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
    }

    public static OrderItemDto from(
            String orderItemUuid,
            OrderItemVo orderItemVo
    ) {
        return OrderItemDto.builder()
                .orderListUuid(orderItemUuid)
                .productUuid(orderItemVo.getProductUuid())
                .productOptionUuid(orderItemVo.getProductOptionUuid())
                .quantity(orderItemVo.getQuantity())
                .totalOriginPrice(orderItemVo.getTotalOriginPrice())
                .totalPurchasePrice(orderItemVo.getTotalPurchasePrice())
                .build();
    }
}
