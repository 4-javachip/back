package com.starbucks.back.shippingaddress.dto.in;

import com.starbucks.back.shippingaddress.vo.in.RequestDeleteShippingAddressVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteShippingAddressDto {

    private String shippingAddressUuid;

    @Builder
    public RequestDeleteShippingAddressDto(String shippingAddressUuid) {
        this.shippingAddressUuid = shippingAddressUuid;
    }

    public static RequestDeleteShippingAddressDto of(RequestDeleteShippingAddressVo requestDeleteShippingAddressVo) {
        return RequestDeleteShippingAddressDto.builder()
                .shippingAddressUuid(requestDeleteShippingAddressVo.getShippingAddressUuid())
                .build();
    }
}
