package com.starbucks.back.order.dto.out;

import com.starbucks.back.order.domain.OrderDetail;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ResponseOrderDetailByOrderItemDto {

    private String orderListUuid;
    private String name;
    private String thumbnail;
    private Integer totalOriginPrice;
    private Integer discountRate;
    private Integer totalPurchasePrice;
    private Integer quantity;

    @Builder
    public ResponseOrderDetailByOrderItemDto(
            String orderListUuid,
            String name,
            String thumbnail,
            Integer totalOriginPrice,
            Integer discountRate,
            Integer totalPurchasePrice,
            Integer quantity
    ) {
        this.orderListUuid = orderListUuid;
        this.name = name;
        this.thumbnail = thumbnail;
        this.totalOriginPrice = totalOriginPrice;
        this.discountRate = discountRate;
        this.totalPurchasePrice = totalPurchasePrice;
        this.quantity = quantity;
    }

    public static ResponseOrderDetailByOrderItemDto from(
            String orderListUuid,
            String name,
            String thumbnail,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            Integer quantity
    ) {
        Integer discountRate = 100 - (totalPurchasePrice * 100 / totalOriginPrice);
        return ResponseOrderDetailByOrderItemDto.builder()
                .orderListUuid(orderListUuid)
                .name(name)
                .thumbnail(thumbnail)
                .totalOriginPrice(totalOriginPrice)
                .discountRate(discountRate)
                .totalPurchasePrice(totalPurchasePrice)
                .quantity(quantity)
                .build();

    }

    public OrderDetail toEntity(String orderListUuid) {
        return OrderDetail.builder()
                .orderListUuid(orderListUuid)
                .orderDetailUuid(UUID.randomUUID().toString())
                .name(name)
                .thumbnail(thumbnail)
                .totalOriginPrice(totalOriginPrice)
                .discountRate(discountRate)
                .totalPurchasePrice(totalPurchasePrice)
                .quantity(quantity)
                .build();
    }
}
