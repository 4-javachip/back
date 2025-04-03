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

    /**
     * 유저 UUID로 배송지List 조회
     * @param userUuid
     * @return
     */
    @Override
    public List<ResponseReadUserShippingAddressDto> getUserShippingAddressListByUserUuid(String userUuid) {
        return userShippingAddressRepository.findByUserUuidAndDefaultedFalseAndDeletedFalse(userUuid)
                .stream()
                .map(ResponseReadUserShippingAddressDto::from)
                .toList();
    }
}
