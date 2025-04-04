package com.starbucks.back.shippingaddress.dto.in;

import com.starbucks.back.shippingaddress.vo.in.RequestDeleteShippingAddressVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteShippingAddressDto {

    private String userUuid;
    private String shippingAddressUuid;

    @Builder
    public RequestDeleteShippingAddressDto(
            String userUuid,
            String shippingAddressUuid
    ) {
        this.userUuid = userUuid;
        this.shippingAddressUuid = shippingAddressUuid;
    }

    public static RequestDeleteShippingAddressDto from (
            String userUuid,
            RequestDeleteShippingAddressVo requestDeleteShippingAddressVo
    ) {
        return RequestDeleteShippingAddressDto.builder()
                .userUuid(userUuid)
                .shippingAddressUuid(requestDeleteShippingAddressVo.getShippingAddressUuid())
                .build();
    }
}
