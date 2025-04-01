package com.starbucks.back.cart.presentation;

import com.starbucks.back.cart.application.CartService;
import com.starbucks.back.cart.dto.out.ResponseCartDto;
import com.starbucks.back.cart.vo.out.ResponseCartVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * 장바구니 조회
     */
    @GetMapping("/{userUuid}")
    @Operation(summary = "GetCartByUserUuid API", description = "GetCartByUserUuid API 입니다.", tags = {"Cart-Service"})
    public BaseResponseEntity<ResponseCartVo> getCart(@PathVariable("userUuid") String userUuid) {
        ResponseCartDto responseCartDto = cartService.getCartByUserUuid(userUuid);
        return new BaseResponseEntity<>(responseCartDto.toVo());
    }
}
