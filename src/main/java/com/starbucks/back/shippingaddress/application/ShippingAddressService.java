package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddresdsDto;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateqShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;

public interface ShippingAddressService {
    void addShippingAddress(RequestShippingAddressDto requestShippingAddressDto);
    ResponseReadShippingAddressDto getShippingAddressByUuid(String Uuid);
    void updateShippingAddress(RequestUpdateqShippingAddressDto requestShippingAddressDto);
    void deleteShippingAddress(RequestDeleteShippingAddresdsDto requestDeleteShippingAddresdsDto);
}
