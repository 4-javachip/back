package com.starbucks.back.order.dto.out;

import com.starbucks.back.order.domain.OrderDetail;
import com.starbucks.back.order.vo.out.ResponseReadOrderDetailVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadOrderDetailDto {

    private String orderListUuid;
    private String orderDetailUuid;
    private String name;
    private String thumbnail;
    private Integer price;
    private Integer discountRate;
    private Integer discountPrice;
    private Integer quantity;

    @Builder
    public ResponseReadOrderDetailDto(
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

    // entity => dto
    public static ResponseReadOrderDetailDto from(OrderDetail orderDetail) {
        return ResponseReadOrderDetailDto.builder()
                .orderListUuid(orderDetail.getOrderListUuid())
                .orderDetailUuid(orderDetail.getOrderDetailUuid())
                .name(orderDetail.getName())
                .thumbnail(orderDetail.getThumbnail())
                .price(orderDetail.getPrice())
                .discountRate(orderDetail.getDiscountRate())
                .discountPrice(orderDetail.getDiscountPrice())
                .quantity(orderDetail.getQuantity())
                .build();
    }

    // dto => vo
    public static ResponseReadOrderDetailVo toVo(ResponseReadOrderDetailDto responseReadOrderDetailDto) {
        return ResponseReadOrderDetailVo.builder()
                .orderListUuid(responseReadOrderDetailDto.getOrderListUuid())
                .orderDetailUuid(responseReadOrderDetailDto.getOrderDetailUuid())
                .name(responseReadOrderDetailDto.getName())
                .thumbnail(responseReadOrderDetailDto.getThumbnail())
                .price(responseReadOrderDetailDto.getPrice())
                .discountRate(responseReadOrderDetailDto.getDiscountRate())
                .discountPrice(responseReadOrderDetailDto.getDiscountPrice())
                .quantity(responseReadOrderDetailDto.getQuantity())
                .build();
    }
}
