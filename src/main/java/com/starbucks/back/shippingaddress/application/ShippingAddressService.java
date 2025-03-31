package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;

import java.util.List;

public interface ShippingAddressService {
    void addShippingAddress(RequestShippingAddressDto requestShippingAddressDto);
    ResponseReadShippingAddressDto getShippingAddressByUuid(String Uuid);
}
