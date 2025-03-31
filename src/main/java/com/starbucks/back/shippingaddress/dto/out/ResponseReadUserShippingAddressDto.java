package com.starbucks.back.shippingaddress.dto.out;

import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import com.starbucks.back.shippingaddress.vo.out.ResponseReadShippingAddressUuidVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadUserShippingAddressDto {
    private final String shippingAddressUuid;
    private final Boolean defaulted;

    @Builder
    public ResponseReadUserShippingAddressDto(String shippingAddressUuid, Boolean defaulted) {
        this.shippingAddressUuid = shippingAddressUuid;
        this.defaulted = defaulted;
    }

    // entity => dto
    public static ResponseReadUserShippingAddressDto from(UserShippingAddress userShippingAddress) {
        return ResponseReadUserShippingAddressDto.builder()
                .shippingAddressUuid(userShippingAddress.getShippingAddressUuid())
                .defaulted(userShippingAddress.getDefaulted())
                .build();
    }

    // dto => vo
    public ResponseReadShippingAddressUuidVo toVo() {
        return ResponseReadShippingAddressUuidVo.builder()
                .shippingAddressUuid(shippingAddressUuid)
                .defaulted(defaulted)
                .build();
    }
}
