package com.starbucks.back.order.dto.out;

import com.starbucks.back.order.domain.OrderDetail;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ResponseOrderDetailByOrderItemDto {

    private String orderListUuid;
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
    public ResponseOrderDetailByOrderItemDto(
            String orderListUuid,
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

    public static ResponseOrderDetailByOrderItemDto from(
            String orderListUuid,
            String productUuid,
            String productOptionUuid,
            String name,
            String thumbnail,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            Integer quantity,
            String sizeName,
            String colorName
    ) {
        Integer discountRate = 100 - (totalPurchasePrice * 100 / totalOriginPrice);
        return ResponseOrderDetailByOrderItemDto.builder()
                .orderListUuid(orderListUuid)
                .productUuid(productUuid)
                .productOptionUuid(productOptionUuid)
                .name(name)
                .thumbnail(thumbnail)
                .totalOriginPrice(totalOriginPrice)
                .discountRate(discountRate)
                .totalPurchasePrice(totalPurchasePrice)
                .quantity(quantity)
                .sizeName(sizeName)
                .colorName(colorName)
                .build();

    }

    public OrderDetail toEntity(String orderListUuid, String productOptionUuid) {
        return OrderDetail.builder()
                .orderListUuid(orderListUuid)
                .orderDetailUuid(UUID.randomUUID().toString())
                .productUuid(productUuid)
                .productOptionUuid(productOptionUuid)
                .name(name)
                .thumbnail(thumbnail)
                .totalOriginPrice(totalOriginPrice)
                .discountRate(discountRate)
                .totalPurchasePrice(totalPurchasePrice)
                .quantity(quantity)
                .sizeName(sizeName)
                .colorName(colorName)
                .build();
    }
}
