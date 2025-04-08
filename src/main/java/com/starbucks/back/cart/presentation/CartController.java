package com.starbucks.back.cart.presentation;

import com.starbucks.back.cart.application.CartService;
import com.starbucks.back.cart.dto.in.*;
import com.starbucks.back.cart.dto.out.ResponseCartDto;
import com.starbucks.back.cart.vo.in.*;
import com.starbucks.back.cart.vo.out.ResponseCartVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * 장바구니 조회(userUuid)
     */
    @GetMapping("/user/{userUuid}")
    @Operation(summary = "GetCartByUserUuid API", description = "GetCartByUserUuid API 입니다.", tags = {"Cart-Service"})
    public BaseResponseEntity<List<ResponseCartVo>> getCart(@PathVariable("userUuid") String userUuid) {
        List<ResponseCartVo> result = cartService.getCartListByUserUuid(userUuid)
                .stream()
                .map(ResponseCartDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
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
    public BaseResponseEntity<Void> updateCart(
            @RequestHeader("userUuid") String userUuid,
            @RequestBody RequestUpdateCartCountVo requestUpdateCartCountVo
    ) {
        RequestUpdateCartCountDto requestUpdateCartCountDto = RequestUpdateCartCountDto.
                from(userUuid, requestUpdateCartCountVo);
        cartService.updateCart(requestUpdateCartCountDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 장바구니 체크박스 수정
     */
    @PutMapping("/checked")
    @Operation(summary = "UpdateCartChecked API", description = "UpdateCartChecked API 입니다.", tags = {"Cart-Service"})
    public BaseResponseEntity<Void> updateCartChecked(
            @RequestHeader("userUuid") String userUuid,
            @RequestBody RequestUpdateCartCheckedVo requestUpdateCartCheckedVo
    ) {
        RequestUpdateCartCheckedDto requestUpdateCartCheckedDto = RequestUpdateCartCheckedDto.from(
                userUuid,
                requestUpdateCartCheckedVo
        );
        cartService.updateCartChecked(requestUpdateCartCheckedDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 장바구니 삭제
     */
    @DeleteMapping
    @Operation(summary = "DeleteCart API", description = "DeleteCart API 입니다.", tags = {"Cart-Service"})
    public BaseResponseEntity<Void> deleteCart(
            @RequestHeader("userUuid") String userUuid,
            @RequestBody RequestDeleteCartVo requestCartVo
    ) {
        RequestDeleteCartDto requestDeleteCartDto = RequestDeleteCartDto.from(userUuid, requestCartVo);
        cartService.deleteCart(requestDeleteCartDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
