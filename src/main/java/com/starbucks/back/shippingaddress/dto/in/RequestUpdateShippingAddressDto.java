package com.starbucks.back.shippingaddress.dto.in;

import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import com.starbucks.back.shippingaddress.vo.in.RequestUpdateShippingAddressVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateShippingAddressDto {
    private String userUuid;
    private String shippingAddressUuid;
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
    public RequestUpdateShippingAddressDto(
            String userUuid,
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
        this.userUuid = userUuid;
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

    public ShippingAddress updateShippingAddress(ShippingAddress shippingAddress) {
        return ShippingAddress.builder()
                .id(shippingAddress.getId())
                .shippingAddressUuid(shippingAddressUuid)
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

    public UserShippingAddress updateUserShippingAddress(UserShippingAddress userShippingAddress) {
        return UserShippingAddress.builder()
                .id(userShippingAddress.getId())
                .userUuid(userUuid)
                .shippingAddressUuid(shippingAddressUuid)
                .defaulted(defaulted)
                .build();
    }



    public static RequestUpdateShippingAddressDto from(
            String userUuid,
            RequestUpdateShippingAddressVo requestUpdateShippingAddressVo
    ) {
        return RequestUpdateShippingAddressDto.builder()
                .userUuid(userUuid)
                .shippingAddressUuid(requestUpdateShippingAddressVo.getShippingAddressUuid())
                .addressName(requestUpdateShippingAddressVo.getAddressName())
                .recipientName(requestUpdateShippingAddressVo.getRecipientName())
                .zipCode(requestUpdateShippingAddressVo.getZipCode())
                .baseAddress(requestUpdateShippingAddressVo.getBaseAddress())
                .detailAddress(requestUpdateShippingAddressVo.getDetailAddress())
                .phoneNumber(requestUpdateShippingAddressVo.getPhoneNumber())
                .secondPhoneNumber(requestUpdateShippingAddressVo.getSecondPhoneNumber())
                .shippingNote(requestUpdateShippingAddressVo.getShippingNote())
                .defaulted(requestUpdateShippingAddressVo.getDefaulted())
                .build();
    }
}
