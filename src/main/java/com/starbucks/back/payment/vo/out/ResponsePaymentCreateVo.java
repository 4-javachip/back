package com.starbucks.back.payment.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponsePaymentCreateVo {

    private String checkoutUrl;
    private String paymentUuid;

    @Builder
    public ResponsePaymentCreateVo(
            String checkoutUrl,
            String paymentUuid
    ) {
        this.checkoutUrl = checkoutUrl;
        this.paymentUuid = paymentUuid;
    }
}
