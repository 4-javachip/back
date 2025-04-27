package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressAndUserDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressWithDefaultedDto;

public interface ShippingAddressService {
    void addShippingAddress(
            String shippingAddressUuid,
            RequestShippingAddressAndUserDto requestShippingAddressAndUserDto
    );
    ResponseReadShippingAddressWithDefaultedDto getShippingAddressByShippingAddressUuid(String shippingAddressUuid);
    void updateShippingAddress(RequestUpdateShippingAddressDto requestShippingAddressDto);
}
