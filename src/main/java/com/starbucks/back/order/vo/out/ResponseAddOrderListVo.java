package com.starbucks.back.order.vo.out;

import com.starbucks.back.payment.domain.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseAddOrderListVo {

    private String orderListUuid;
    private PaymentStatus paymentStatus;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private List<AddedOrderItemVo> orderItems;
    private String recipientName;
    private String zipCode;
    private String baseAddress;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    public ResponseAddOrderListVo(
            String orderListUuid,
            PaymentStatus paymentStatus,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            List<AddedOrderItemVo> orderItems,
            String recipientName,
            String zipCode,
            String baseAddress,
            String detailAddress,
            String phoneNumber
    ) {
        this.orderListUuid = orderListUuid;
        this.paymentStatus = paymentStatus;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.orderItems = orderItems;
        this.recipientName = recipientName;
        this.zipCode = zipCode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
    }
}
