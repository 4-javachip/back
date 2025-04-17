package com.starbucks.back.order.vo.in;

import com.starbucks.back.order.domain.enums.PaymentStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class RequestAddOrderListVo {
    private List<String> orderItemUuids;
    private String shippingAddressUuid;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private String paymentUuid;
    private PaymentStatus paymentStatus;
}
