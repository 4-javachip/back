package com.starbucks.back.shippingaddress.vo;

import lombok.Getter;

//
@Getter
public class RequestShippingAddressVo {

    private Long id;
    private String addressName;
    private String recipientName;
    private String zipCode;
    private String baseAddress;
    private String detailAddress;
    private String phoneNumber;
    private String secondPhoneNumber;
    private String shippingNote;

}
