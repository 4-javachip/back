package com.starbucks.back.cart.presentation;

import com.starbucks.back.cart.application.CartService;
import com.starbucks.back.cart.dto.in.*;
import com.starbucks.back.cart.dto.out.ResponseCartDto;
import com.starbucks.back.cart.vo.in.*;
import com.starbucks.back.cart.vo.out.ResponseCartVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final SecurityUtil securityUtil;

    /**
     * 장바구니 조회(userUuid)
     */
    @GetMapping("/user")
    @Operation(summary = "GetCartByUserUuid API", description = "GetCartByUserUuid API 입니다.", tags = {"Cart-Service"})
    public BaseResponseEntity<List<ResponseCartVo>> getCart() {
        String userUuid = securityUtil.getCurrentUserUuid();

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
        String userUuid = securityUtil.getCurrentUserUuid();

        RequestAddCartDto requestAddCartDto = RequestAddCartDto.from(userUuid, requestAddCartVo);
        cartService.addCart(requestAddCartDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 장바구니 수량 수정
     */
    @PutMapping("/quantity")
    @Operation(summary = "UpdateCart API", description = "UpdateCart API 입니다.", tags = {"Cart-Service"})
    public BaseResponseEntity<Void> updateCart(
            @RequestBody RequestUpdateCartCountVo requestUpdateCartCountVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();

        RequestUpdateCartCountDto requestUpdateCartCountDto = RequestUpdateCartCountDto.
                from(userUuid, requestUpdateCartCountVo);
        cartService.updateCart(requestUpdateCartCountDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 장바구니 체크박스 수정 by UserUuid, cartUuid
     */
    @PutMapping("/checked")
    @Operation(summary = "UpdateCartChecked API", description = "UpdateCartChecked API 입니다.", tags = {"Cart-Service"})
    public BaseResponseEntity<Void> updateCartChecked(
            @RequestBody RequestUpdateCartCheckedVo requestUpdateCartCheckedVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();

        RequestUpdateCartCheckedDto requestUpdateCartCheckedDto = RequestUpdateCartCheckedDto.from(
                userUuid,
                requestUpdateCartCheckedVo
        );
        cartService.updateCartChecked(requestUpdateCartCheckedDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 장바구니 삭제 by UserUuid, cartUuid
     */
    @DeleteMapping
    @Operation(summary = "DeleteCart API", description = "DeleteCart API 입니다.", tags = {"Cart-Service"})
    public BaseResponseEntity<Void> deleteCart(
            @RequestBody RequestDeleteCartVo requestCartVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();

        RequestDeleteCartDto requestDeleteCartDto = RequestDeleteCartDto.from(userUuid, requestCartVo);
        cartService.deleteCart(requestDeleteCartDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 장바구니 전체 삭제 by UserUuid
     */
    @DeleteMapping("/all")
    @Operation(summary = "DeleteAllCart API", description = "장바구니 전체 삭제 by UserUuid", tags = {"Cart-Service"})
    public BaseResponseEntity<Void> deleteAllCart() {
        String userUuid = securityUtil.getCurrentUserUuid();

        cartService.deleteAllCart(userUuid);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
