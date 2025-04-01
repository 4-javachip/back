package com.starbucks.back.cart.presentation;

import com.starbucks.back.cart.application.CartService;
import com.starbucks.back.cart.dto.in.RequestAddCartDto;
import com.starbucks.back.cart.dto.in.RequestCartDto;
import com.starbucks.back.cart.dto.in.RequestUpdateCartCheckedDto;
import com.starbucks.back.cart.dto.in.RequestUpdateCartCountDto;
import com.starbucks.back.cart.dto.out.ResponseCartDto;
import com.starbucks.back.cart.vo.in.RequestAddCartVo;
import com.starbucks.back.cart.vo.in.RequestCartVo;
import com.starbucks.back.cart.vo.in.RequestUpdateCartCheckedVo;
import com.starbucks.back.cart.vo.in.RequestUpdateCartCountVo;
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
     * 장바구니 조회(userUuid)
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
    public BaseResponseEntity<Void> addCart(@RequestBody RequestAddCartVo requestAddCartVo) {
        RequestAddCartDto requestAddCartDto = RequestAddCartDto.from(requestAddCartVo);
        cartService.addCart(requestAddCartDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 장바구니 수량 수정
     */
    @PutMapping("/quantity")
    @Operation(summary = "UpdateCart API", description = "UpdateCart API 입니다.", tags = {"Cart-Service"})
    public BaseResponseEntity<Void> updateCart(@RequestBody RequestUpdateCartCountVo requestUpdateCartCountVo) {
        RequestUpdateCartCountDto requestUpdateCartCountDto = RequestUpdateCartCountDto.from(requestUpdateCartCountVo);
        cartService.updateCart(requestUpdateCartCountDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 장바구니 체크박스 수정
     */
    @PutMapping("/checked")
    @Operation(summary = "UpdateCartChecked API", description = "UpdateCartChecked API 입니다.", tags = {"Cart-Service"})
    public BaseResponseEntity<Void> updateCartChecked(@RequestBody RequestUpdateCartCheckedVo requestUpdateCartCheckedVo) {
        RequestUpdateCartCheckedDto requestUpdateCartCheckedDto = RequestUpdateCartCheckedDto.from(requestUpdateCartCheckedVo);
        cartService.updateCartChecked(requestUpdateCartCheckedDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
