package com.starbucks.back.shippingaddress.dto.in;

import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.vo.in.RequestShippingAddressVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class RequestShippingAddressDto {

    private String addressName;
    private String recipientName;
    private String zipCode;
    private String baseAddress;
    private String detailAddress;
    private String phoneNumber;
    private String secondPhoneNumber;
    private String shippingNote;
    private Boolean defaulted;

    @Builder
    public RequestShippingAddressDto(
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



    public ShippingAddress toEntity(){
        return ShippingAddress.builder()
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

    public ShippingAddress updateEntity(ShippingAddress shippingAddress) {
        return ShippingAddress.builder()
                .id(shippingAddress.getId())
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
                .defaulted(requestShippingAddressVo.getDefaulted())
                .build();
    }
}

