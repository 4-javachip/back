package com.starbucks.back.order.dto.out;

import com.querydsl.core.Tuple;
import com.starbucks.back.order.domain.OrderDetail;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ResponseOrderDetailByCartUuidDto {

    private String orderListUuid;
    private String orderDetailUuid;
    private String name;
    private String thumbnail;
    private Integer price;
    private Integer discountRate;
    private Integer discountPrice;
    private Integer quantity;

    @Builder
    public ResponseOrderDetailByCartUuidDto(
            String orderListUuid,
            String orderDetailUuid,
            String name,
            String thumbnail,
            Integer price,
            Integer discountRate,
            Integer discountPrice,
            Integer quantity
    ) {
        this.orderListUuid = orderListUuid;
        this.orderDetailUuid = orderDetailUuid;
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
        this.discountRate = discountRate;
        this.discountPrice = discountPrice;
        this.quantity = quantity;
    }

    public static ResponseOrderDetailByCartUuidDto from(
            String orderListUuid,
            String name,
            String thumbnail,
            Integer price,
            Integer discountRate,
            Integer discountPrice,
            Integer quantity
    ) {
        return ResponseOrderDetailByCartUuidDto.builder()
                .orderListUuid(orderListUuid)
                .orderDetailUuid(UUID.randomUUID().toString())
                .name(name)
                .thumbnail(thumbnail)
                .price(price)
                .discountRate(discountRate)
                .discountPrice(discountPrice)
                .quantity(quantity)
                .build();
    }

    public OrderDetail toEntity(String orderListUuid) {
        return OrderDetail.builder()
                .orderListUuid(orderListUuid)
                .orderDetailUuid(UUID.randomUUID().toString())
                .name(name)
                .thumbnail(thumbnail)
                .price(price)
                .discountRate(discountRate)
                .discountPrice(discountPrice)
                .quantity(quantity)
                .build();
    }

}
