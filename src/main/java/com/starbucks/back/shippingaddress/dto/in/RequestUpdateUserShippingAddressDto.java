package com.starbucks.back.shippingaddress.dto.in;

import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import com.starbucks.back.shippingaddress.vo.in.RequestUpdateUserShippingAddressVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestUpdateUserShippingAddressDto {

    private String shippingAddressUuid;
    private Boolean defaulted;
    private String userUuid;

    @Builder
    public RequestUpdateUserShippingAddressDto(String shippingAddressUuid, Boolean defaulted, String userUuid) {
        this.shippingAddressUuid = shippingAddressUuid;
        this.defaulted = defaulted;
        this.userUuid = userUuid;
    }

    public static RequestUpdateUserShippingAddressDto of(
            String userUuid,
            RequestUpdateUserShippingAddressVo requestUpdateUserShippingAddressVo) {
        return RequestUpdateUserShippingAddressDto.builder()
                .shippingAddressUuid(requestUpdateUserShippingAddressVo.getShippingAddressUuid())
                .defaulted(requestUpdateUserShippingAddressVo.getDefaulted())
                .userUuid(userUuid)
                .build();
    }

    public UserShippingAddress updateUserShippingAddress(UserShippingAddress userShippingAddress) {
        System.out.println("defaulted: " + defaulted);
        return UserShippingAddress.builder()
                .id(userShippingAddress.getId())
                .shippingAddressUuid(shippingAddressUuid)
                .defaulted(defaulted)
                .userUuid(userUuid)
                .build();
    }
}
