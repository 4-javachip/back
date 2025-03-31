package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;

import java.util.List;

public interface UserShippingAddressService {
    List<ResponseReadUserShippingAddressDto> getShippingAddressUuidListByUserUuidAndDeletedFalse(String userUuid);
}
