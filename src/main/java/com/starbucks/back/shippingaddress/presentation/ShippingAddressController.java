package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.shippingaddress.application.ShippingAddressService;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.vo.out.ResponseReadShippingAddressUuidVo;
import com.starbucks.back.shippingaddress.vo.out.ResponseShippingAddressVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping-address")
@RequiredArgsConstructor
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    /**
     * 배송지 추가
     * @param requestShippingAddressDto
     */
    @Operation(summary = "AddShippingAddress API", description = "AddShippingAddress API 입니다.", tags = {"ShippingAddress-Service"})
    @Transactional
    @PostMapping
    public void addShippingAddress(@RequestBody RequestShippingAddressDto requestShippingAddressDto) {
        shippingAddressService.addShippingAddress(requestShippingAddressDto);
    }

    /**
     * 배송지 uuid로 배송지 조회
     * @param uuid
     */
    @Operation(summary = "GetShippingAddressByUuid API", description = "GetShippingAddressByUuid API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/{uuid}")
    public ResponseShippingAddressVo getShippingAddress(@PathVariable("uuid") String uuid) {
        ResponseReadShippingAddressDto responseReadShippingAddressDto = shippingAddressService.getShippingAddressByUuid(uuid);
        return responseReadShippingAddressDto.toVo();
    }
}
