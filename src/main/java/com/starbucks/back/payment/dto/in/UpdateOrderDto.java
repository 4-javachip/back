package com.starbucks.back.payment.dto.in;

import com.starbucks.back.payment.domain.Payment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateOrderDto {

    private String userUuid;
    private String orderListUuid;
    private String paymentUuid;
    private String orderName;
    private String method;
    private String paymentStatus;

    @Builder
    public UpdateOrderDto(
            String userUuid,
            String orderListUuid,
            String paymentUuid,
            String orderName,
            String method,
            String paymentStatus
    ) {
        this.userUuid = userUuid;
        this.orderListUuid = orderListUuid;
        this.paymentUuid = paymentUuid;
        this.orderName = orderName;
        this.method = method;
        this.paymentStatus = paymentStatus;
    }

    public static UpdateOrderDto from(
            Payment payment
    ) {
        return UpdateOrderDto.builder()
                .userUuid(payment.getUserUuid())
                .orderListUuid(payment.getOrderListUuid())
                .paymentUuid(payment.getPaymentUuid())
                .orderName(payment.getOrderName())
                .method(payment.getMethod())
                .paymentStatus(payment.getStatus().getDescription())
                .build();
    }
}
