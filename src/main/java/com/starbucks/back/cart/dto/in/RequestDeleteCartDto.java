package com.starbucks.back.cart.dto.in;

import com.starbucks.back.cart.domain.Cart;
import com.starbucks.back.cart.vo.in.RequestDeleteCartVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestDeleteCartDto {

    private String userUuid;
    private String cartUuid;

    @Builder
    public RequestDeleteCartDto(String userUuid, String cartUuid) {
        this.userUuid = userUuid;
        this.cartUuid = cartUuid;
    }

    public static RequestDeleteCartDto from(
            String userUuid,
            RequestDeleteCartVo requestDeleteCartVo
    ) {
        return RequestDeleteCartDto.builder()
                .userUuid(userUuid)
                .cartUuid(requestDeleteCartVo.getCartUuid())
                .build();
    }

    public static RequestDeleteCartDto from(
            String userUuid,
            String cartUuid
    ) {
        return RequestDeleteCartDto.builder()
                .userUuid(userUuid)
                .cartUuid(cartUuid)
                .build();
    }
}
