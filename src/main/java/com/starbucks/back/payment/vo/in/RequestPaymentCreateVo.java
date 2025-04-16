package com.starbucks.back.payment.vo.in;

import lombok.Getter;

@Getter
public class RequestPaymentCreateVo {

    private Integer amount;
    private String method;
    private String orderName;
    private Integer saleAmount;
}
