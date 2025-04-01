package com.starbucks.back.cart.application;

import com.starbucks.back.cart.dto.in.RequestAddCartDto;
import com.starbucks.back.cart.dto.in.RequestUpdateCartCountDto;
import com.starbucks.back.cart.dto.out.ResponseCartDto;

public interface CartService {

    ResponseCartDto getCartByUserUuid(String userUuid);
    void addCart(RequestAddCartDto requestAddCartDto);
    void updateCart(RequestUpdateCartCountDto requestUpdateCartCountDto);

}
