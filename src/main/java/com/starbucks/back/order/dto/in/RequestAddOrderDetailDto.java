package com.starbucks.back.order.dto.in;

import com.starbucks.back.order.vo.in.RequestAddOrderListVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RequestAddOrderDetailDto {
    private String cartUuid;
    private List<String> checkedCartUuidList;
    private String shippingAddressUuid;
    private Integer totalOrderPrice;
    private Integer totalAmount;
    private String paymentUuid;

    @Builder
    public RequestAddOrderDetailDto(String cartUuid, List<String> checkedCartUuidList, String shippingAddressUuid, Integer totalOrderPrice, Integer totalAmount, String paymentUuid) {
        this.cartUuid = cartUuid;
        this.checkedCartUuidList = checkedCartUuidList;
        this.shippingAddressUuid = shippingAddressUuid;
        this.totalOrderPrice = totalOrderPrice;
        this.totalAmount = totalAmount;
        this.paymentUuid = paymentUuid;
    }

    public static RequestAddOrderDetailDto of(
            String cartUuid,
            RequestAddOrderListVo requestAddOrderListVo
    ) {
            return RequestAddOrderDetailDto.builder()
                .cartUuid(cartUuid)
                .checkedCartUuidList(requestAddOrderListVo.getCheckedCartUuidList())
                .shippingAddressUuid(requestAddOrderListVo.getShippingAddressUuid())
                .totalOrderPrice(requestAddOrderListVo.getTotalOrderPrice())
                .totalAmount(requestAddOrderListVo.getTotalAmount())
                .paymentUuid(requestAddOrderListVo.getPaymentUuid())
                .build();
    }
}
