package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.shippingaddress.application.ShippingAddressService;
import com.starbucks.back.shippingaddress.application.UserShippingAddressService;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;
import com.starbucks.back.shippingaddress.vo.out.ResponseReadShippingAddressUuidVo;
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

    @GetMapping("/user/{userUuid}")
    public List<ResponseReadShippingAddressUuidVo> getShippingAddressUuidListByUserUuid(@PathVariable("userUuid") String userUuid) {
        List<ResponseReadShippingAddressUuidVo> result = userShippingAddressService.getShippingAddressUuidListByUserUuid(userUuid)
                .stream()
                .map(ResponseReadUserShippingAddressDto::toVo)
                .toList();
        return result;
    }
}
