package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressAndUserDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateUserShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressWithDefaultedDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;
import com.starbucks.back.shippingaddress.infrastructure.UserShippingAddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
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
        return userShippingAddressRepository.findAllByUserUuidAndDeletedFalse(userUuid)
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
    public ResponseReadShippingAddressWithDefaultedDto getDefaultShippingAddressByUserUuid(String userUuid) {
        // 기본 배송지 UUID 조회
        UserShippingAddress userShippingAddress = userShippingAddressRepository
                .findByUserUuidAndDefaultedTrueAndDeletedFalse(userUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        log.info("userShippingAddress : {}", userShippingAddress);
        // 배송지 uuid 로 배송지 조회 (배송지 service 의 getShippingAddressByShippingAddressUuid 메서드 사용)
        return shippingAddressService.
                getShippingAddressByShippingAddressUuid(userShippingAddress.getShippingAddressUuid());
    }

    /**
     * 유저 배송지 추가
     * @param requestShippingAddressAndUserDto
     */
    @Transactional
    @Override
    public void addUserShippingAddress(RequestShippingAddressAndUserDto requestShippingAddressAndUserDto) {
        // userShippingAddress 선언
        UserShippingAddress userShippingAddress = null;
        // 이미 등록한 defaulted = true 배송지의 존재 여부 파악.
        Boolean exists =  userShippingAddressRepository.existsByUserUuidAndDeletedFalse(
                    requestShippingAddressAndUserDto.getUserUuid()
                );

        // defaulted=true면, 나머지 전부 defaulted=false로 수정
        if (requestShippingAddressAndUserDto.getDefaulted()) {
            userShippingAddress = requestShippingAddressAndUserDto
                    .toUserShippingAddressEntity(true);
            userShippingAddressRepository.resetDefaultedByUserUuid(requestShippingAddressAndUserDto.getUserUuid());
        }
        // defaulted=false면, exists에 따라 defaulted 다르게 저장
        else {
            if (exists) {
                userShippingAddress = requestShippingAddressAndUserDto
                        .toUserShippingAddressEntity(false);
            } else {
                userShippingAddress = requestShippingAddressAndUserDto
                        .toUserShippingAddressEntity(true);
            }
        }

        // 유저배송지 추가
        userShippingAddressRepository.save(userShippingAddress);
        // 배송지 추가
        shippingAddressService.addShippingAddress(
                userShippingAddress.getShippingAddressUuid(),       // 유저배송지 테이블에서 생성한 uuid 가져와서 저장
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
        if (!userShippingAddressRepository.existsByUserUuidAndShippingAddressUuidAndDeletedFalse(
                    requestUpdateShippingAddressDto.getUserUuid(),
                    requestUpdateShippingAddressDto.getShippingAddressUuid()
                )) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_OPTION);
        }

        shippingAddressService.updateShippingAddress(requestUpdateShippingAddressDto);
    }

    /**
     * 배송지 삭제 by userUuid, shippingAddressUuid
     * @param requestDeleteShippingAddressDto
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
     * @param dtoList
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
