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

    @Override
    public void addShippingAddress(RequestShippingAddressDto requestShippingAddressDto) {
        shippingAddressRepository.save(requestShippingAddressDto.toEntity());
    }

    @Override
    public ResponseReadShippingAddressDto getShippingAddressByUuid(String Uuid) {
        ShippingAddress shippingAddress = shippingAddressRepository.findByShippingAddressUuidAndDeletedFalse(Uuid)
                .orElseThrow(() -> new EntityNotFoundException("해당 배송지를 찾을 수 없습니다."));
        return ResponseReadShippingAddressDto.from(shippingAddress);
    }
}
