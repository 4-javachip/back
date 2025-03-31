package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.shippingaddress.application.ShippingAddressService;
import com.starbucks.back.shippingaddress.application.UserShippingAddressService;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;
import com.starbucks.back.shippingaddress.vo.out.ResponseReadShippingAddressUuidVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping-address")
@RequiredArgsConstructor
public class UserShippingAddressController {

    private final UserShippingAddressService userShippingAddressService;

    /**
     * 유저 UUID로 배송지 UUID List 조회
     * @param userUuid
     * @return
     */
    @Operation(summary = "getUserShippingAddressListByUserUuid API", description = "getUserShippingAddressListByUserUuid API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/user/{userUuid}")
    public List<ResponseReadShippingAddressUuidVo> getUserShippingAddressListByUserUuid(@PathVariable("userUuid") String userUuid) {
        List<ResponseReadShippingAddressUuidVo> result = userShippingAddressService.getUserShippingAddressListByUserUuid(userUuid)
                .stream()
                .map(ResponseReadUserShippingAddressDto::toVo)
                .toList();
        return result;
    }
}
