package com.starbucks.back.order.dto.in;

import com.starbucks.back.order.vo.in.OrderItemVo;
import com.starbucks.back.order.vo.in.RequestAddOrderListVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RequestAddOrderDetailDto {
    private String cartUuid;
    private List<OrderItemVo> orderItems;
    private String shippingAddressUuid;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private String paymentUuid;

    @Builder
    public RequestAddOrderDetailDto(
            String cartUuid,
            List<OrderItemVo> orderItems,
            String shippingAddressUuid,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            String paymentUuid
    ) {
        this.cartUuid = cartUuid;
        this.orderItems = orderItems;
        this.shippingAddressUuid = shippingAddressUuid;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.paymentUuid = paymentUuid;
    }

    public static RequestAddOrderDetailDto of(
            String cartUuid,
            RequestAddOrderListVo requestAddOrderListVo
    ) {
            return RequestAddOrderDetailDto.builder()
                    .cartUuid(cartUuid)
                    .orderItems(requestAddOrderListVo.getOrderItems())
                    .shippingAddressUuid(requestAddOrderListVo.getShippingAddressUuid())
                    .totalOriginPrice(requestAddOrderListVo.getTotalOriginPrice())
                    .totalPurchasePrice(requestAddOrderListVo.getTotalPurchasePrice())
                    .paymentUuid(requestAddOrderListVo.getPaymentUuid())
                    .build();
    }
}
