package com.starbucks.back.shippingaddress.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadShippingAddressListVo {

    private String shippingAddressUuid;

    @Builder
    public ResponseReadShippingAddressListVo(String shippingAddressUuid) {
        this.shippingAddressUuid = shippingAddressUuid;
    }
}
