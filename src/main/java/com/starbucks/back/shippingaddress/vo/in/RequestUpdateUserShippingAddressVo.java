package com.starbucks.back.shippingaddress.vo.in;

import lombok.Getter;

@Getter
public class RequestUpdateUserShippingAddressVo {

    private String shippingAddressUuid;
    private Boolean defaulted;
}
