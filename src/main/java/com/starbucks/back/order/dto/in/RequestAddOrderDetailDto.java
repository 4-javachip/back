package com.starbucks.back.order.dto.in;

import com.starbucks.back.order.vo.in.RequestAddOrderListVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RequestAddOrderDetailDto {
    private String cartUuid;
    private List<String> orderItemUuids;
    private String shippingAddressUuid;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private String paymentUuid;

    @Builder
    public RequestAddOrderDetailDto(
            String cartUuid,
            List<String> orderItemUuids,
            String shippingAddressUuid,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            String paymentUuid
    ) {
        this.cartUuid = cartUuid;
        this.orderItemUuids = orderItemUuids;
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
                    .orderItemUuids(requestAddOrderListVo.getOrderItemUuids())
                    .shippingAddressUuid(requestAddOrderListVo.getShippingAddressUuid())
                    .totalOriginPrice(requestAddOrderListVo.getTotalOriginPrice())
                    .totalPurchasePrice(requestAddOrderListVo.getTotalPurchasePrice())
                    .paymentUuid(requestAddOrderListVo.getPaymentUuid())
                    .build();
    }
}
