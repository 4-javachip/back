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
//    private final ShippingAddressRepository shippingAddressRepository;
    private final ShippingAddressService shippingAddressService;

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

        // 배송지 UUID로 배송지 조회 (배송지 service의 getShippingAddressByUuid 메서드 사용)
        ResponseReadShippingAddressDto result = shippingAddressService.
                getShippingAddressByUuid(userShippingAddress.getShippingAddressUuid());

        return result;
    }

    /**
     * 배송지 전부 삭제 by userUuid
     */
    @Override
    public void deleteAllShippingAddressByUserUuid(String userUuid) {
        // 배송지 목록 삭제
        shippingAddressService.deleteAllShippingAddressByUserUuid(userUuid);
        // 유저-배송지 목록 삭제
        userShippingAddressRepository.bulkSoftDeleteUserShippingAddressesByUserUuid(userUuid);
    }
}
