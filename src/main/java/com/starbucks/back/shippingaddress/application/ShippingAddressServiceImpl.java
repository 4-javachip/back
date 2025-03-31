package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressDto;
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
     * @param requestShippingAddressDto
     */
    @Transactional
    @Override
    public void addShippingAddress(RequestShippingAddressDto requestShippingAddressDto) {
        if (shippingAddressRepository.existsByZipCodeAndBaseAddressAndDetailAddressAndDeletedFalse(
                requestShippingAddressDto.getZipCode(),
                requestShippingAddressDto.getBaseAddress(),
                requestShippingAddressDto.getDetailAddress())
        ) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_OPTION);
        }
        shippingAddressRepository.save(requestShippingAddressDto.toEntity());
    }

    /**
     * 배송지 uuid로 배송지 조회
     * @param uuid
     * @return
     */
    @Transactional
    @Override
    public ResponseReadShippingAddressDto getShippingAddressByUuid(String uuid) {
        ShippingAddress shippingAddress = shippingAddressRepository.findByShippingAddressUuidAndDeletedFalse(uuid)
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
        ShippingAddress shippingAddress = shippingAddressRepository.findByShippingAddressUuidAndDeletedFalse(requestShippingAddressDto.getShippingAddressUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        shippingAddressRepository.save(requestShippingAddressDto.updateShippingAddress(shippingAddress));
    }
    /**
     * 배송지 삭제
     * @param 
     */
    @Transactional
    @Override
    public void deleteShippingAddress(RequestDeleteShippingAddressDto requestDeleteShippingAddressDto) {
        ShippingAddress shippingAddress = shippingAddressRepository.findByShippingAddressUuidAndDeletedFalse(requestDeleteShippingAddressDto.getShippingAddressUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        shippingAddress.softDelete();
        System.out.println("Hello World");
    }
}
