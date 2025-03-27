package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressDto;


public interface ShippingAddressService {
    void addShippingAddress(RequestShippingAddressDto requestShippingAddressDto);
}
