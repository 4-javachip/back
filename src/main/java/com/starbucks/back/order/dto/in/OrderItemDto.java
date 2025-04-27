package com.starbucks.back.order.dto.in;

import com.starbucks.back.order.vo.in.OrderItemVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OrderItemDto {
    private String orderListUuid;
    private String productUuid;
    private String productOptionUuid;
    private Integer quantity;
    private Integer price;
    private Integer totalPrice;

    @Builder
    public OrderItemDto(
            String orderListUuid,
            String productUuid,
            String productOptionUuid,
            Integer quantity,
            Integer price,
            Integer totalPrice
    ) {
        this.orderListUuid = orderListUuid;
        this.productUuid = productUuid;
        this.productOptionUuid = productOptionUuid;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public static OrderItemDto from(
            String orderListUuid,
            OrderItemVo orderItemVo
    ) {
        return OrderItemDto.builder()
                .orderListUuid(orderListUuid)
                .productUuid(orderItemVo.getProductUuid())
                .productOptionUuid(orderItemVo.getProductOptionUuid())
                .quantity(orderItemVo.getQuantity())
                .price(orderItemVo.getPrice())
                .totalPrice(orderItemVo.getTotalPrice())
                .build();
    }
}
