package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.shippingaddress.application.ShippingAddressService;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressWithDefaultedDto;
import com.starbucks.back.shippingaddress.vo.out.ResponseShippingAddressVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipping-address")
@RequiredArgsConstructor
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;
    /**
     * 배송지 객체 조회 by shippingAddressUuid
     * @param shippingAddressUuid
     */
    @Operation(summary = "배송지 객체 조회 API", description = "배송지 객체 조회 API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/{shippingAddressUuid}")
    public BaseResponseEntity<ResponseShippingAddressVo> getShippingAddress(
            @PathVariable("shippingAddressUuid") String shippingAddressUuid
    ) {
        ResponseReadShippingAddressWithDefaultedDto responseReadShippingAddressWithDefaultedDto =
                shippingAddressService.getShippingAddressByShippingAddressUuid(shippingAddressUuid);

        return new BaseResponseEntity<>(responseReadShippingAddressWithDefaultedDto.toVo());
    }
}
