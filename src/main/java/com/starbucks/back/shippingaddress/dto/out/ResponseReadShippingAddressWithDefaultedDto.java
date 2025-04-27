package com.starbucks.back.shippingaddress.dto.out;

import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.vo.out.ResponseShippingAddressVo;
import lombok.Builder;
import lombok.Getter;

// id가 주어졌을 때 배송지 조회 dto
@Getter
public class ResponseReadShippingAddressWithDefaultedDto {
    private final Long id;
    private final String shippingAddressUuid;
    private final String addressName;
    private final String recipientName;
    private final String zipCode;
    private final String baseAddress;
    private final String detailAddress;
    private final String phoneNumber;
    private final String secondPhoneNumber;
    private final String shippingNote;
    private final Boolean defaulted;


    @Builder
    public ResponseReadShippingAddressWithDefaultedDto(
            Long id,
            String shippingAddressUuid,
            String addressName,
            String recipientName,
            String zipCode,
            String baseAddress,
            String detailAddress,
            String phoneNumber,
            String secondPhoneNumber,
            String shippingNote,
            Boolean defaulted
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
        this.defaulted = defaulted;
    }

    // dto => vo
    public ResponseShippingAddressVo toVo() {
        return ResponseShippingAddressVo.builder()
                .shippingAddressUuid(shippingAddressUuid)
                .addressName(addressName)
                .recipientName(recipientName)
                .zipCode(zipCode)
                .baseAddress(baseAddress)
                .detailAddress(detailAddress)
                .phoneNumber(phoneNumber)
                .secondPhoneNumber(secondPhoneNumber)
                .shippingNote(shippingNote)
                .defaulted(defaulted)
                .build();

    }
}
