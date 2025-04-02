package com.starbucks.back.cart.dto.in;

import com.starbucks.back.cart.domain.Cart;
import com.starbucks.back.cart.vo.in.RequestDeleteCartVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestDeleteCartDto {

    private String cartUuid;

    @Builder
    public RequestDeleteCartDto(String cartUuid) {
        this.cartUuid = cartUuid;
    }

    public static RequestDeleteCartDto of(RequestDeleteCartVo requestDeleteCartVo) {
        return RequestDeleteCartDto.builder()
                .cartUuid(requestDeleteCartVo.getCartUuid())
                .build();
    }
}
