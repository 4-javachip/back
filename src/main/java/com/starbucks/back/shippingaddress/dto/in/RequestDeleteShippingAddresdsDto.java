package com.starbucks.back.shippingaddress.dto.in;

import com.starbucks.back.shippingaddress.vo.in.RequestDeleteShippingAddressVo;
import com.starbucks.back.shippingaddress.vo.in.RequestUpdateShippingAddressVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteShippingAddresdsDto {

    private String shippingAddressUuid;

    @Builder
    public RequestDeleteShippingAddresdsDto(String shippingAddressUuid) {
        this.shippingAddressUuid = shippingAddressUuid;
    }

    public static RequestDeleteShippingAddresdsDto of(RequestDeleteShippingAddressVo requestDeleteShippingAddressVo) {
        return RequestDeleteShippingAddresdsDto.builder()
                .shippingAddressUuid(requestDeleteShippingAddressVo.getShippingAddressUuid())
                .build();
    }
}
