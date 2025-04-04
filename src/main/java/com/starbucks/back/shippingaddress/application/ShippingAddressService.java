package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressAndUserDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;

public interface ShippingAddressService {
    void addShippingAddress(String shippingAddressUuid, RequestShippingAddressAndUserDto requestShippingAddressAndUserDto);
    ResponseReadShippingAddressDto getShippingAddressByUuid(String Uuid);
    void updateShippingAddress(RequestUpdateShippingAddressDto requestShippingAddressDto);
    void deleteShippingAddress(RequestDeleteShippingAddressDto requestDeleteShippingAddressDto);
    void deleteAllShippingAddressByUserUuid(String userUuid);
}
