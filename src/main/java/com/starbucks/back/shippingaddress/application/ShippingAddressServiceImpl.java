package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressAndUserDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.infrastructure.ShippingAddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    /**
     * 배송지 추가
     * @param requestShippingAddressAndUserDto
     */
    @Transactional
    @Override
    public void addShippingAddress(
            String shippingAddressUuid,
            RequestShippingAddressAndUserDto requestShippingAddressAndUserDto
    ) {
        shippingAddressRepository.save(requestShippingAddressAndUserDto.toShippingAddressEntity(shippingAddressUuid));
    }

    /**
     * 배송지 조회 by shippingAddressUuid
     * @param shippingAddressUuid
     * @return
     */
    @Override
    public ResponseReadShippingAddressDto getShippingAddressByUuid(String shippingAddressUuid) {
        ShippingAddress shippingAddress = shippingAddressRepository
                .findByShippingAddressUuid(shippingAddressUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        
        return ResponseReadShippingAddressDto.from(shippingAddress);
    }

    /**
     * 배송지 수정
     * @param requestShippingAddressDto
     */
    @Transactional
    @Override
    public void updateShippingAddress(RequestUpdateShippingAddressDto requestShippingAddressDto) {
        ShippingAddress shippingAddress = shippingAddressRepository.findByShippingAddressUuid(requestShippingAddressDto.getShippingAddressUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        shippingAddressRepository.save(requestShippingAddressDto.updateShippingAddress(shippingAddress));
    }

//    /**
//     * 배송지 삭제
//     * @param
//     */
//    @Transactional
//    @Override
//    public void deleteShippingAddress(RequestDeleteShippingAddressDto requestDeleteShippingAddressDto) {
//        ShippingAddress shippingAddress = shippingAddressRepository.findByShippingAddressUuid(
//                requestDeleteShippingAddressDto.getShippingAddressUuid()
//                )
//                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
//        shippingAddress.softDelete();
//    }

//    /**
//     * 배송지 전부 삭제 by userUuid
//     */
//    @Override
//    public void deleteAllShippingAddressByUserUuid(String userUuid) {
//        shippingAddressRepository.bulkSoftDeleteShippingAddressesByUserUuid(userUuid);
//    }
}
