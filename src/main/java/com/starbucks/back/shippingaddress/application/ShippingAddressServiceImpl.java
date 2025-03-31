package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddresdsDto;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateqShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;
import com.starbucks.back.shippingaddress.infrastructure.ShippingAddressRepository;
import com.starbucks.back.shippingaddress.infrastructure.UserShippingAddressRepository;
import com.starbucks.back.shippingaddress.vo.in.RequestDeleteShippingAddressVo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void updateShippingAddress(RequestUpdateqShippingAddressDto requestShippingAddressDto) {
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
    public void deleteShippingAddress(RequestDeleteShippingAddresdsDto requestDeleteShippingAddressDto) {
        ShippingAddress shippingAddress = shippingAddressRepository.findByShippingAddressUuidAndDeletedFalse(requestDeleteShippingAddressDto.getShippingAddressUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        shippingAddress.softDelete();
        System.out.println("Hello World");
    }
}
