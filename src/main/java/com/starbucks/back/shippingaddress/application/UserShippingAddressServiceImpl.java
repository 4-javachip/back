package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;
import com.starbucks.back.shippingaddress.infrastructure.ShippingAddressRepository;
import com.starbucks.back.shippingaddress.infrastructure.UserShippingAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserShippingAddressServiceImpl implements UserShippingAddressService{
    private final UserShippingAddressRepository userShippingAddressRepository;
    private final ShippingAddressRepository shippingAddressRepository;
    /**
     * 유저 UUID로 기본 외 배송지List 조회
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

    /**
     * 유저 UUID로 기본배송지 UUID 조회
     * @param userUuid
     * @return
     */
    @Override
    public ResponseReadShippingAddressDto getDefaultShippingAddressByUserUuid(String userUuid) {
        // 기본 배송지 UUID 조회
        UserShippingAddress userShippingAddress = userShippingAddressRepository
                .findByUserUuidAndDefaultedTrueAndDeletedFalse(userUuid);

        // 배송지 UUID로 배송지 조회
        ShippingAddress shippingAddress = shippingAddressRepository
                .findByShippingAddressUuidAndDeletedFalse(userShippingAddress.getShippingAddressUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));

        return ResponseReadShippingAddressDto.from(shippingAddress);
    }
}
