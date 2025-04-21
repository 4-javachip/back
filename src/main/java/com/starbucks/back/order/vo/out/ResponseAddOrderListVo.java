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
    private String addressName;
    private String recipientName;
    private String zipCode;
    private String baseAddress;
    private String detailAddress;
    private String phoneNumber;
    private String shippingNote;
    private String secondPhoneNumber;

    @Builder
    public ResponseAddOrderListVo(
            String orderListUuid,
            PaymentStatus paymentStatus,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            List<AddedOrderItemVo> orderItems,
            String addressName,
            String recipientName,
            String zipCode,
            String baseAddress,
            String detailAddress,
            String phoneNumber,
            String shippingNote,
            String secondPhoneNumber
    ) {
        this.orderListUuid = orderListUuid;
        this.paymentStatus = paymentStatus;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.orderItems = orderItems;
        this.addressName = addressName;
        this.recipientName = recipientName;
        this.zipCode = zipCode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.shippingNote = shippingNote;
        this.secondPhoneNumber = secondPhoneNumber;
    }
}
