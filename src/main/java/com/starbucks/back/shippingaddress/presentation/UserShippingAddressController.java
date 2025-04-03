package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.shippingaddress.application.UserShippingAddressService;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;
import com.starbucks.back.shippingaddress.vo.out.ResponseReadShippingAddressListVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping-address")
@RequiredArgsConstructor
public class UserShippingAddressController {

    private final UserShippingAddressService userShippingAddressService;

    /**
     * 유저 UUID로 기본외배송지 UUID List 조회
     * @param userUuid
     * @return
     */
    @Operation(summary = "getUserShippingAddressListByUserUuid API", description = "getUserShippingAddressListByUserUuid API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/user/not-default")
    public BaseResponseEntity<List<ResponseReadShippingAddressListVo>> getUserShippingAddressListByUserUuid(@RequestHeader("userUuid") String userUuid) {
        List<ResponseReadShippingAddressListVo> result = userShippingAddressService.getUserShippingAddressListByUserUuid(userUuid)
                .stream()
                .map(ResponseReadUserShippingAddressDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }
}
