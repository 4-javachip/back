package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.shippingaddress.dto.in.RequestUpdateUserShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;

import java.util.List;

public interface UserShippingAddressService {
    List<ResponseReadUserShippingAddressDto> getUserShippingAddressListByUserUuid(String userUuid);
    ResponseReadShippingAddressDto getDefaultShippingAddressByUserUuid(String userUuid);
    void deleteAllShippingAddressByUserUuid(String userUuid);
    void updateUserShippingAddressDefaulted(RequestUpdateUserShippingAddressDto requestUpdateUserShippingAddressDto);
}
