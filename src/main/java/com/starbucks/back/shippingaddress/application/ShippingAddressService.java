package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressDto;


public interface ShippingAddressService {
    void createShippingAddress(RequestShippingAddressDto requestShippingAddressDto);
//    void deleteShippingAddress(Long shippingAddressId);
    //    이렇게 하는 게 맞나?
//    void updateShippingAddress(RequestShippingAddressDto requestShippingAddressDto);
//    ResponseReadShippingAddressDto readShippingAddress(Long shippingAddressId);
}
