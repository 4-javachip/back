package com.starbucks.back.cart.application;

import com.starbucks.back.cart.dto.in.RequestAddCartDto;
import com.starbucks.back.cart.dto.in.RequestDeleteCartDto;
import com.starbucks.back.cart.dto.in.RequestUpdateCartCheckedDto;
import com.starbucks.back.cart.dto.in.RequestUpdateCartCountDto;
import com.starbucks.back.cart.dto.out.ResponseCartDto;

import java.util.List;

public interface CartService {

    List<ResponseCartDto> getCartListByUserUuid(String userUuid);
    void addCart(RequestAddCartDto requestAddCartDto);
    void updateCart(RequestUpdateCartCountDto requestUpdateCartCountDto);
    void updateCartChecked(RequestUpdateCartCheckedDto requestUpdateCartCheckedDto);
    void deleteCart(RequestDeleteCartDto requestDeleteCartDto);
}
