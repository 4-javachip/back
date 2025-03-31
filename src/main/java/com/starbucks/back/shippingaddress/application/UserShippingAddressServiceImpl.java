package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;
import com.starbucks.back.shippingaddress.infrastructure.UserShippingAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserShippingAddressServiceImpl implements UserShippingAddressService{
    private final UserShippingAddressRepository userShippingAddressRepository;

    @Override
    public List<ResponseReadUserShippingAddressDto> getShippingAddressUuidListByUserUuidAndDeletedFalse(String userUuid) {
        return userShippingAddressRepository.findByUserUuidAndDeletedFalse(userUuid)
                .stream()
                .map(ResponseReadUserShippingAddressDto::from)
                .toList();
    }
}
