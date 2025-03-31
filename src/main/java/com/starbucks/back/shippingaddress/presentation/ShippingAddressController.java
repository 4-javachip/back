package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.shippingaddress.application.ShippingAddressService;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateqShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.vo.in.RequestUpdateShippingAddressVo;
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
    public BaseResponseEntity<Void> addShippingAddress(@RequestBody RequestShippingAddressDto requestShippingAddressDto) {
        shippingAddressService.addShippingAddress(requestShippingAddressDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 배송지 uuid로 배송지 조회
     * @param uuid
     */
    @Operation(summary = "GetShippingAddressByUuid API", description = "GetShippingAddressByUuid API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/{uuid}")
    public BaseResponseEntity<ResponseShippingAddressVo> getShippingAddress(@PathVariable("uuid") String uuid) {
        ResponseReadShippingAddressDto responseReadShippingAddressDto = shippingAddressService.getShippingAddressByUuid(uuid);
        return new BaseResponseEntity<>(responseReadShippingAddressDto.toVo());
    }

    /**
     * 배송지 수정
     * @param requestUpdateShippingAddressVo
     */
    @PutMapping
    public BaseResponseEntity<Void> updateShippingAddressByUuid(
            @RequestBody RequestUpdateShippingAddressVo requestUpdateShippingAddressVo) {
        shippingAddressService
                .updateShippingAddress(RequestUpdateqShippingAddressDto
                        .from(requestUpdateShippingAddressVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
