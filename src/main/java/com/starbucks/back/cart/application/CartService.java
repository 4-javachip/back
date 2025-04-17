package com.starbucks.back.cart.application;

import com.starbucks.back.cart.dto.in.*;
import com.starbucks.back.cart.dto.out.ResponseCartDto;

import java.util.List;

public interface CartService {

    List<ResponseCartDto> getCartListByUserUuid(String userUuid);
    void addCart(RequestAddCartDto requestAddCartDto);
    void updateCart(RequestUpdateCartCountDto requestUpdateCartCountDto);
    void updateCartChecked(RequestUpdateCartCheckedDto requestUpdateCartCheckedDto);
    void updateAllCartChecked(RequestUpdateAllCartCheckedDto requestUpdateAllCartCheckedDto);
    void deleteCart(RequestDeleteCartDto requestDeleteCartDto);
    List<ResponseCartDto> getCartListByCartUuidList(List<String> cartUuidList);
    void deleteAllCart(String userUuid);
 
}
