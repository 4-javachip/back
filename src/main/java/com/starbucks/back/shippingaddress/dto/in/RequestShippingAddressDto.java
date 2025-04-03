package com.starbucks.back.shippingaddress.dto.in;

import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.vo.in.RequestShippingAddressVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@NoArgsConstructor
public class RequestShippingAddressDto {

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
    public RequestShippingAddressDto(
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

    // entity => dto
    public ShippingAddress toEntity(){
        return ShippingAddress.builder()
                .shippingAddressUuid(UUID.randomUUID().toString())
                .addressName(addressName)
                .recipientName(recipientName)
                .zipCode(zipCode)
                .baseAddress(baseAddress)
                .detailAddress(detailAddress)
                .phoneNumber(phoneNumber)
                .secondPhoneNumber(secondPhoneNumber)
                .shippingNote(shippingNote)
                .build();
    }

    // vo => dto
    public static RequestShippingAddressDto from (RequestShippingAddressVo requestShippingAddressVo) {
        return RequestShippingAddressDto.builder()
                .addressName(requestShippingAddressVo.getAddressName())
                .recipientName(requestShippingAddressVo.getRecipientName())
                .zipCode(requestShippingAddressVo.getZipCode())
                .baseAddress(requestShippingAddressVo.getBaseAddress())
                .detailAddress(requestShippingAddressVo.getDetailAddress())
                .phoneNumber(requestShippingAddressVo.getPhoneNumber())
                .secondPhoneNumber(requestShippingAddressVo.getSecondPhoneNumber())
                .shippingNote(requestShippingAddressVo.getShippingNote())
                .build();
    }
}

