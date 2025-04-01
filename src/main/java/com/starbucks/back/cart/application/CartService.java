package com.starbucks.back.cart.application;

import com.starbucks.back.cart.dto.in.RequestCartDto;
import com.starbucks.back.cart.dto.out.ResponseCartDto;

public interface CartService {

    ResponseCartDto getCartByUserUuid(String userUuid);
    void addCart(RequestCartDto requestCartDto);
}
