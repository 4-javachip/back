package com.starbucks.back.payment.vo.in;

import lombok.Getter;

@Getter
public class RequestPaymentConfirmVo {
    private String paymentKey;
    private String orderId;
    private Integer amount;
}
