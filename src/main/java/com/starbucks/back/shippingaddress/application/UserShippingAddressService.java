package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressAndUserDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateUserShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressWithDefaultedDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;

import java.util.List;

public interface UserShippingAddressService {
    List<ResponseReadUserShippingAddressDto> getUserShippingAddressAllListByUserUuid(String userUuid);
    List<ResponseReadUserShippingAddressDto> getUserShippingAddressListByUserUuid(String userUuid);
    ResponseReadShippingAddressWithDefaultedDto getDefaultShippingAddressByUserUuid(String userUuid);
    void addUserShippingAddress(RequestShippingAddressAndUserDto requestShippingAddressAndUserDto);
    void updateShippingAddress(RequestUpdateShippingAddressDto requestUpdateShippingAddressDto);
    void deleteShippingAddress(RequestDeleteShippingAddressDto requestDeleteShippingAddressDto);
    void deleteAllShippingAddressByUserUuid(String userUuid);
    void updateUserShippingAddressDefaulted(
            List<RequestUpdateUserShippingAddressDto> requestUpdateUserShippingAddressDtoList
    );
}
