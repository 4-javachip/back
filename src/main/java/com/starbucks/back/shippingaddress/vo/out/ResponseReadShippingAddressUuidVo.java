package com.starbucks.back.shippingaddress.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadShippingAddressUuidVo {

    private String shippingAddressUuid;
    private Boolean defaulted;

    @Builder
    public ResponseReadShippingAddressUuidVo(String shippingAddressUuid, Boolean defaulted) {
        this.shippingAddressUuid = shippingAddressUuid;
        this.defaulted = defaulted;
    }
}
