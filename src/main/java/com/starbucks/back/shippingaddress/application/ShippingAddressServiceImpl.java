package com.starbucks.back.shippingaddress.application;

import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;
import com.starbucks.back.shippingaddress.infrastructure.ShippingAddressRepository;
import com.starbucks.back.shippingaddress.infrastructure.UserShippingAddressRepository;
import jakarta.persistence.EntityNotFoundException;
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
    @Override
    public void addShippingAddress(RequestShippingAddressDto requestShippingAddressDto) {
        shippingAddressRepository.save(requestShippingAddressDto.toEntity());
    }

    /**
     * 배송지 uuid로 배송지 조회
     * @param uuid
     * @return
     */
    @Override
    public ResponseReadShippingAddressDto getShippingAddressByUuid(String uuid) {
        ShippingAddress shippingAddress = shippingAddressRepository.findByShippingAddressUuidAndDeletedFalse(uuid)
                .orElseThrow(() -> new EntityNotFoundException("해당 배송지를 찾을 수 없습니다."));
        return ResponseReadShippingAddressDto.from(shippingAddress);
    }
}
