package com.starbucks.back.shippingaddress.dto.in;

import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import com.starbucks.back.shippingaddress.vo.in.RequestShippingAddressAndUserVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@NoArgsConstructor
public class RequestShippingAddressAndUserDto {

    private String addressName;
    private String recipientName;
    private String zipCode;
    private String baseAddress;
    private String detailAddress;
    private String phoneNumber;
    private String secondPhoneNumber;
    private String shippingNote;
    private Boolean defaulted;          // userShippingAddress 테이블에서만 사용
    private String userUuid;            // userShippingAddress 테이블에서만 사용

    @Builder
    public RequestShippingAddressAndUserDto(
            String addressName,
            String recipientName,
            String zipCode,
            String baseAddress,
            String detailAddress,
            String phoneNumber,
            String secondPhoneNumber,
            String shippingNote,
            Boolean defaulted,
            String userUuid
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
        this.userUuid = userUuid;
    }

    // dto => entity. 배송지 엔티티 생성
    public ShippingAddress toShippingAddressEntity(String shippingAddressUuid){
        return ShippingAddress.builder()
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

    // dto => entity. 유저배송지 엔티티 생성
    public UserShippingAddress toUserShippingAddressEntity() {
        return UserShippingAddress.builder()
                .shippingAddressUuid(UUID.randomUUID().toString())
                .defaulted(defaulted)
                .userUuid(userUuid)
                .build();
    }

    // vo => dto
    public static RequestShippingAddressAndUserDto from (
            String userUuid,
            RequestShippingAddressAndUserVo requestShippingAddressAndUserVo
    ) {
        return RequestShippingAddressAndUserDto.builder()
                .addressName(requestShippingAddressAndUserVo.getAddressName())
                .recipientName(requestShippingAddressAndUserVo.getRecipientName())
                .zipCode(requestShippingAddressAndUserVo.getZipCode())
                .baseAddress(requestShippingAddressAndUserVo.getBaseAddress())
                .detailAddress(requestShippingAddressAndUserVo.getDetailAddress())
                .phoneNumber(requestShippingAddressAndUserVo.getPhoneNumber())
                .secondPhoneNumber(requestShippingAddressAndUserVo.getSecondPhoneNumber())
                .shippingNote(requestShippingAddressAndUserVo.getShippingNote())
                .defaulted(requestShippingAddressAndUserVo.getDefaulted())
                .userUuid(userUuid)
                .build();
    }
}

