package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressAndUserDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateUserShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;
import com.starbucks.back.shippingaddress.infrastructure.UserShippingAddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserShippingAddressServiceImpl implements UserShippingAddressService{
    private final UserShippingAddressRepository userShippingAddressRepository;
    private final ShippingAddressService shippingAddressService;

    /**
     * 기본외배송지리스트 조회 by userUuid
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
     * 기본배송지 객체 조회 by userUuid
     * @param userUuid
     * @return
     */
    @Override
    public ResponseReadShippingAddressDto getDefaultShippingAddressByUserUuid(String userUuid) {
        // 기본 배송지 UUID 조회
        UserShippingAddress userShippingAddress = userShippingAddressRepository
                .findByUserUuidAndDefaultedTrueAndDeletedFalse(userUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));

        // 배송지 UUID로 배송지 조회 (배송지 service의 getShippingAddressByUuid 메서드 사용)
        ResponseReadShippingAddressDto result = shippingAddressService.
                getShippingAddressByUuid(userShippingAddress.getShippingAddressUuid());

        return result;
    }

    /**
     * 유저 배송지 추가
     * @param requestShippingAddressAndUserDto
     */
    @Transactional
    @Override
    public void addUserShippingAddress(RequestShippingAddressAndUserDto requestShippingAddressAndUserDto) {
        UserShippingAddress userShippingAddress = requestShippingAddressAndUserDto.toUserShippingAddressEntity();
        // 유저배송지 추가
        userShippingAddressRepository.save(userShippingAddress);
        // 배송지 추가
        shippingAddressService.addShippingAddress(
                userShippingAddress.getShippingAddressUuid(),
                requestShippingAddressAndUserDto
        );
    }

    /**
     * 배송지 삭제 by userUuid, shippingAddressUuid
     * @param
     */
    @Transactional
    @Override
    public void deleteShippingAddress(RequestDeleteShippingAddressDto requestDeleteShippingAddressDto) {
        // 유저 배송지 삭제
        UserShippingAddress userShippingAddress = userShippingAddressRepository
                .findByUserUuidAndShippingAddressUuidAndDeletedFalse(
                        requestDeleteShippingAddressDto.getUserUuid(),
                        requestDeleteShippingAddressDto.getShippingAddressUuid()
                )
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        userShippingAddress.softDelete();
    }

    /**
     * 배송지 전부 삭제 by userUuid
     */
    @Transactional
    @Override
    public void deleteAllShippingAddressByUserUuid(String userUuid) {
        // 유저-배송지 목록 삭제
        userShippingAddressRepository.bulkSoftDeleteUserShippingAddressesByUserUuid(userUuid);
    }

    /**
     * 배송지 기본 배송지로 변경
     * @param requestUpdateUserShippingAddressDto
     */
    @Transactional
    @Override
    public void updateUserShippingAddressDefaulted(
            RequestUpdateUserShippingAddressDto requestUpdateUserShippingAddressDto
    ) {
        UserShippingAddress userShippingAddress = userShippingAddressRepository
                .findByUserUuidAndShippingAddressUuidAndDeletedFalse(
                        requestUpdateUserShippingAddressDto.getUserUuid(),
                        requestUpdateUserShippingAddressDto.getShippingAddressUuid()
                )
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        userShippingAddressRepository.save(
                requestUpdateUserShippingAddressDto
                .updateUserShippingAddress(userShippingAddress)
        );
    }
}
