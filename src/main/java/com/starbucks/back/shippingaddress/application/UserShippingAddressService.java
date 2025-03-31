package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;

import java.util.List;

public interface UserShippingAddressService {
    List<ResponseReadUserShippingAddressDto> getShippingAddressUuidListByUserUuid(String userUuid);
}
