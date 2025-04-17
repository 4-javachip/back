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
    private Integer totalOriginPrice;
    private Integer discountRate;
    private Integer totalPurchasePrice;
    private Integer quantity;

    @Builder
    public ResponseReadOrderDetailDto(
            String orderListUuid,
            String orderDetailUuid,
            String name,
            String thumbnail,
            Integer totalOriginPrice,
            Integer discountRate,
            Integer totalPurchasePrice,
            Integer quantity
    ) {
        this.orderListUuid = orderListUuid;
        this.orderDetailUuid = orderDetailUuid;
        this.name = name;
        this.thumbnail = thumbnail;
        this.totalOriginPrice = totalOriginPrice;
        this.discountRate = discountRate;
        this.totalPurchasePrice = totalPurchasePrice;
        this.quantity = quantity;
    }

    // entity => dto
    public static ResponseReadOrderDetailDto from(OrderDetail orderDetail) {
        return ResponseReadOrderDetailDto.builder()
                .orderListUuid(orderDetail.getOrderListUuid())
                .orderDetailUuid(orderDetail.getOrderDetailUuid())
                .name(orderDetail.getName())
                .thumbnail(orderDetail.getThumbnail())
                .totalOriginPrice(orderDetail.getTotalOriginPrice())
                .discountRate(orderDetail.getDiscountRate())
                .totalPurchasePrice(orderDetail.getTotalPurchasePrice())
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
                .totalOriginPrice(responseReadOrderDetailDto.getTotalOriginPrice())
                .discountRate(responseReadOrderDetailDto.getDiscountRate())
                .totalPurchasePrice(responseReadOrderDetailDto.getTotalPurchasePrice())
                .quantity(responseReadOrderDetailDto.getQuantity())
                .build();
    }
}
