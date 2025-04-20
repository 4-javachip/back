package com.starbucks.back.order.vo.out;

import com.starbucks.back.payment.domain.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseAddOrderListVo {

    private String shippingAddressUuid;
    private String orderListUuid;
    private String proquctUuid;
    private PaymentStatus paymentStatus;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private List<AddedOrderItemVo> orderItems;

    @Builder
    public ResponseAddOrderListVo(
            String shippingAddressUuid,
            String orderListUuid,
            String proquctUuid,
            PaymentStatus paymentStatus,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            List<AddedOrderItemVo> orderItems
    ) {
        this.shippingAddressUuid = shippingAddressUuid;
        this.orderListUuid = orderListUuid;
        this.proquctUuid = proquctUuid;
        this.paymentStatus = paymentStatus;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.orderItems = orderItems;
    }
}
