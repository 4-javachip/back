package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.shippingaddress.application.ShippingAddressService;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.vo.in.RequestUpdateShippingAddressVo;
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
    @Operation(summary = "GetShippingAddressByUuid API", description = "GetShippingAddressByUuid API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/{shippingAddressUuid}")
    public BaseResponseEntity<ResponseShippingAddressVo> getShippingAddress(
            @PathVariable("shippingAddressUuid") String shippingAddressUuid
    ) {
        ResponseReadShippingAddressDto responseReadShippingAddressDto = shippingAddressService
                .getShippingAddressByUuid(shippingAddressUuid);

        return new BaseResponseEntity<>(responseReadShippingAddressDto.toVo());
    }

    /**
     * 배송지 수정 by shippingAddressUuid
     * @param requestUpdateShippingAddressVo
     */
    @PutMapping
    public BaseResponseEntity<Void> updateShippingAddressByUuid(
            @RequestBody RequestUpdateShippingAddressVo requestUpdateShippingAddressVo
    ) {
        shippingAddressService.updateShippingAddress(
                        RequestUpdateShippingAddressDto.from(requestUpdateShippingAddressVo));

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
