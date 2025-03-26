package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.shippingaddress.application.ShippingAddressService;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseShippingAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipping-address")
@RequiredArgsConstructor
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    @PostMapping
    public void createShippingAddress(@RequestBody RequestShippingAddressDto requestShippingAddressDto) {
        shippingAddressService.createShippingAddress(requestShippingAddressDto);
    }
}
