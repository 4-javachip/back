package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressAndUserDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateShippingAddressDto;
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
     * 배송지 List 조회 by userUuid
     * @param userUuid
     * @return
     */
    @Override
    public List<ResponseReadUserShippingAddressDto> getUserShippingAddressAllListByUserUuid(String userUuid) {
        return userShippingAddressRepository.findByUserUuidAndDeletedFalse(userUuid)
                .stream()
                .map(ResponseReadUserShippingAddressDto::from)
                .toList();
    }

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
        // 등록한 배송지 존재 여부 파악 후, defaulted 판단.
        Boolean defaulted =  !userShippingAddressRepository.existsByUserUuidAndDeletedFalse(
                    requestShippingAddressAndUserDto.getUserUuid()
                );
        UserShippingAddress userShippingAddress = requestShippingAddressAndUserDto
                .toUserShippingAddressEntity(defaulted);
        // 유저배송지 추가
        userShippingAddressRepository.save(userShippingAddress);
        // 배송지 추가
        shippingAddressService.addShippingAddress(
                userShippingAddress.getShippingAddressUuid(),
                requestShippingAddressAndUserDto
        );
    }

    /**
     * 배송지 수정 by userUuid, shippingAddressUuid
     * @param requestUpdateShippingAddressDto
     */
    @Transactional
    @Override
    public void updateShippingAddress(RequestUpdateShippingAddressDto requestUpdateShippingAddressDto) {
        if (userShippingAddressRepository.existsByUserUuidAndShippingAddressUuidAndDeletedFalse(
                    requestUpdateShippingAddressDto.getUserUuid(),
                    requestUpdateShippingAddressDto.getShippingAddressUuid()
                )) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_OPTION);
        }

        shippingAddressService.updateShippingAddress(requestUpdateShippingAddressDto);
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
     * 배송지 기본 배송지로 변경 by [{userUuid, shippingAddressUuid, defaulted}, ..]
     * @param
     */
    @Transactional
    @Override
    public void updateUserShippingAddressDefaulted(
            List<RequestUpdateUserShippingAddressDto> dtoList
    ) {
        for (RequestUpdateUserShippingAddressDto requestUpdateUserShippingAddressDto : dtoList) {

            UserShippingAddress userShippingAddress = userShippingAddressRepository
                .findByUserUuidAndShippingAddressUuidAndDeletedFalse(
                        requestUpdateUserShippingAddressDto.getUserUuid(),
                        requestUpdateUserShippingAddressDto.getShippingAddressUuid()
                )
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
            userShippingAddressRepository.save(requestUpdateUserShippingAddressDto
                    .updateUserShippingAddress(userShippingAddress
                    )
            );
        }
    }
}
