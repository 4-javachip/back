package com.starbucks.back.order.dto.out;

import com.starbucks.back.order.domain.OrderDetail;
import com.starbucks.back.order.vo.out.ResponseReadOrderDetailVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadOrderDetailDto {

    private String orderListUuid;
    private String orderDetailUuid;
    private String productUuid;
    private String productOptionUuid;
    private String name;
    private String thumbnail;
    private Integer totalOriginPrice;
    private Integer discountRate;
    private Integer totalPurchasePrice;
    private Integer quantity;
    private String sizeName;
    private String colorName;

    @Builder
    public ResponseReadOrderDetailDto(
            String orderListUuid,
            String orderDetailUuid,
            String productUuid,
            String productOptionUuid,
            String name,
            String thumbnail,
            Integer totalOriginPrice,
            Integer discountRate,
            Integer totalPurchasePrice,
            Integer quantity,
            String sizeName,
            String colorName
    ) {
        this.orderListUuid = orderListUuid;
        this.orderDetailUuid = orderDetailUuid;
        this.productUuid = productUuid;
        this.productOptionUuid = productOptionUuid;
        this.name = name;
        this.thumbnail = thumbnail;
        this.totalOriginPrice = totalOriginPrice;
        this.discountRate = discountRate;
        this.totalPurchasePrice = totalPurchasePrice;
        this.quantity = quantity;
        this.sizeName = sizeName;
        this.colorName = colorName;
    }

    // entity => dto
    public static ResponseReadOrderDetailDto from(OrderDetail orderDetail) {
        return ResponseReadOrderDetailDto.builder()
                .orderListUuid(orderDetail.getOrderListUuid())
                .orderDetailUuid(orderDetail.getOrderDetailUuid())
                .productUuid(orderDetail.getProductUuid())
                .productOptionUuid(orderDetail.getProductOptionUuid())
                .name(orderDetail.getName())
                .thumbnail(orderDetail.getThumbnail())
                .totalOriginPrice(orderDetail.getTotalOriginPrice())
                .discountRate(orderDetail.getDiscountRate())
                .totalPurchasePrice(orderDetail.getTotalPurchasePrice())
                .quantity(orderDetail.getQuantity())
                .sizeName(orderDetail.getSizeName())
                .colorName(orderDetail.getColorName())
                .build();
    }

    // dto => vo
    public static ResponseReadOrderDetailVo toVo(ResponseReadOrderDetailDto responseReadOrderDetailDto) {
        return ResponseReadOrderDetailVo.builder()
                .orderListUuid(responseReadOrderDetailDto.getOrderListUuid())
                .orderDetailUuid(responseReadOrderDetailDto.getOrderDetailUuid())
                .productUuid(responseReadOrderDetailDto.getProductUuid())
                .productOptionUuid(responseReadOrderDetailDto.getProductOptionUuid())
                .name(responseReadOrderDetailDto.getName())
                .thumbnail(responseReadOrderDetailDto.getThumbnail())
                .price(responseReadOrderDetailDto.getTotalOriginPrice())
                .discountRate(responseReadOrderDetailDto.getDiscountRate())
                .totalPrice(responseReadOrderDetailDto.getTotalPurchasePrice())
                .quantity(responseReadOrderDetailDto.getQuantity())
                .sizeName(responseReadOrderDetailDto.getSizeName())
                .colorName(responseReadOrderDetailDto.getColorName())
                .build();
    }
}
