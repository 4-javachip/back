package com.starbucks.back.order.dto.out;

import com.starbucks.back.order.domain.OrderList;
import com.starbucks.back.order.vo.out.ResponseReadOrderListVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseReadOrderListDto {
    private String orderListUuid;
    private String orderCode;
    private LocalDateTime createdAt;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private Integer discountRate;
    private String addressName;
    private String recipientName;
    private String zipCode;
    private String baseAddress;
    private String detailAddress;
    private String phoneNumber;
    private String secondPhoneNumber;
    private String shippingNote;
    private String orderName;
    private String method;

    @Builder
    public ResponseReadOrderListDto(
            String orderListUuid,
            String orderCode,
            LocalDateTime createdAt,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            Integer discountRate,
            String addressName,
            String recipientName,
            String zipCode,
            String baseAddress,
            String detailAddress,
            String phoneNumber,
            String secondPhoneNumber,
            String shippingNote,
            String orderName,
            String method
    ) {
        this.orderListUuid = orderListUuid;
        this.orderCode = orderCode;
        this.createdAt = createdAt;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.discountRate = discountRate;
        this.addressName = addressName;
        this.recipientName = recipientName;
        this.zipCode = zipCode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.secondPhoneNumber = secondPhoneNumber;
        this.shippingNote = shippingNote;
        this.orderName = orderName;
        this.method = method;
    }

    // entity => vo
    public static ResponseReadOrderListDto from(OrderList orderList) {
        return ResponseReadOrderListDto.builder()
                .orderListUuid(orderList.getOrderListUuid())
                .orderCode(orderList.getOrderCode())
                .createdAt(orderList.getCreatedAt())
                .totalOriginPrice(orderList.getTotalOriginPrice())
                .totalPurchasePrice(orderList.getTotalPurchasePrice())
                .discountRate(orderList.getDiscountRate())
                .addressName(orderList.getAddressName())
                .recipientName(orderList.getRecipientName())
                .zipCode(orderList.getZipCode())
                .baseAddress(orderList.getBaseAddress())
                .detailAddress(orderList.getDetailAddress())
                .phoneNumber(orderList.getPhoneNumber())
                .secondPhoneNumber(orderList.getSecondPhoneNumber())
                .shippingNote(orderList.getShippingNote())
                .orderName(orderList.getOrderName())
                .method(orderList.getMethod())
                .build();
    }

    // dto => vo
    public ResponseReadOrderListVo toVo() {
        return ResponseReadOrderListVo.builder()
                .orderListUuid(orderListUuid)
                .orderCode(orderCode)
                .createdAt(createdAt)
                .totalOriginPrice(totalOriginPrice)
                .totalPurchasePrice(totalPurchasePrice)
                .discountRate(discountRate)
                .addressName(addressName)
                .recipientName(recipientName)
                .zipCode(zipCode)
                .baseAddress(baseAddress)
                .detailAddress(detailAddress)
                .phoneNumber(phoneNumber)
                .secondPhoneNumber(secondPhoneNumber)
                .shippingNote(shippingNote)
                .orderName(orderName)
                .method(method)
                .build();
    }
}
