package com.starbucks.back.cart.presentation;

import com.starbucks.back.cart.application.CartService;
import com.starbucks.back.cart.dto.in.RequestCartDto;
import com.starbucks.back.cart.dto.out.ResponseCartDto;
import com.starbucks.back.cart.vo.out.ResponseCartVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 장바구니 생성
     */
    @PostMapping
    @Operation(summary = "CreateCart API", description = "CreateCart API 입니다.", tags = {"Cart-Service"})
    public BaseResponseEntity<Void> addCart(@RequestBody RequestCartDto requestCartDto) {
        cartService.addCart(requestCartDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
