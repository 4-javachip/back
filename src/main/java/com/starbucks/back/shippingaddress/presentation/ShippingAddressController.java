package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.shippingaddress.application.ShippingAddressService;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.vo.out.ResponseShippingAddressVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipping-address")
@RequiredArgsConstructor
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    @PostMapping
    public void addShippingAddress(@RequestBody RequestShippingAddressDto requestShippingAddressDto) {
        shippingAddressService.addShippingAddress(requestShippingAddressDto);
    }

    @GetMapping("/{uuid}")
    public ResponseShippingAddressVo getShippingAddress(@PathVariable String uuid) {
        ResponseReadShippingAddressDto responseReadShippingAddressDto = shippingAddressService.getShippingAddressByUuid(uuid);
        return responseReadShippingAddressDto.toVo();
    }
}
