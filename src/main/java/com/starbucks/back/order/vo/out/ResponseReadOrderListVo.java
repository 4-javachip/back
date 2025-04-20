package com.starbucks.back.order.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseReadOrderListVo {
    private String orderListUuid;
    private String orderCode;
    private LocalDateTime createdAt;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private Integer discountRate;
    private String recipientName;
    private String zipCode;
    private String baseAddress;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    public ResponseReadOrderListVo(
            String orderListUuid,
            String orderCode,
            LocalDateTime createdAt,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            Integer discountRate,
            String recipientName,
            String zipCode,
            String baseAddress,
            String detailAddress,
            String phoneNumber
    ) {
        this.orderListUuid = orderListUuid;
        this.orderCode = orderCode;
        this.createdAt = createdAt;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.discountRate = discountRate;
        this.recipientName = recipientName;
        this.zipCode = zipCode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
    }
}
