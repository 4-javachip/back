package com.starbucks.back.shippingaddress.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ResponseShippingAddressVo {

    private Long id;
    private String shippingAddressUuid;
    private String addressName;
    private String recipientName;
    private String zipCode;
    private String baseAddress;
    private String detailAddress;
    private String phoneNumber;
    private String secondPhoneNumber;
    private String shippingNote;

    @Builder
    public ResponseShippingAddressVo(
            Long id,
            String shippingAddressUuid,
            String addressName,
            String recipientName,
            String zipCode,
            String baseAddress,
            String detailAddress,
            String phoneNumber,
            String secondPhoneNumber,
            String shippingNote
    ) {
        this.id = id;
        this.shippingAddressUuid = shippingAddressUuid;
        this.addressName = addressName;
        this.recipientName = recipientName;
        this.zipCode = zipCode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.secondPhoneNumber = secondPhoneNumber;
        this.shippingNote = shippingNote;
    }
}
