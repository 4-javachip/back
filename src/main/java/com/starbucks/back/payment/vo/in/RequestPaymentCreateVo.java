package com.starbucks.back.payment.vo.in;

import lombok.Getter;

@Getter
public class RequestPaymentCreateVo {

    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private String method;
    private String orderName;
    private String orderListUuid;
}
