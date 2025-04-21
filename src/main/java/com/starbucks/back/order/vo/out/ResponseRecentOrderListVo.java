package com.starbucks.back.order.vo.out;

import com.starbucks.back.order.domain.OrderList;
import com.starbucks.back.order.domain.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseRecentOrderListVo {

    private String orderListUuid;
    private PaymentStatus paymentStatus;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private List<RecentOrderItemVo> orderItems;
    private String addressName;
    private String recipientName;
    private String zipCode;
    private String baseAddress;
    private String detailAddress;
    private String phoneNumber;
    private String shippingNote;
    private String secondPhoneNumber;

    @Builder
    public ResponseRecentOrderListVo(
            String orderListUuid,
            PaymentStatus paymentStatus,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            List<RecentOrderItemVo> orderItems,
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

    public static ResponseRecentOrderListVo from(
            OrderList orderList,
            List<RecentOrderItemVo> orderItems
    ) {
        return ResponseRecentOrderListVo.builder()
                .orderListUuid(orderList.getOrderListUuid())
                .paymentStatus(orderList.getPaymentStatus())
                .totalOriginPrice(orderList.getTotalOriginPrice())
                .totalPurchasePrice(orderList.getTotalPurchasePrice())
                .orderItems(orderItems)
                .addressName(orderList.getAddressName())
                .recipientName(orderList.getRecipientName())
                .zipCode(orderList.getZipCode())
                .baseAddress(orderList.getBaseAddress())
                .detailAddress(orderList.getDetailAddress())
                .phoneNumber(orderList.getPhoneNumber())
                .shippingNote(orderList.getShippingNote())
                .secondPhoneNumber(orderList.getSecondPhoneNumber())
                .build();
    }
}
