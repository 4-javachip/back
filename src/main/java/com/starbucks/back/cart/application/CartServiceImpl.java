package com.starbucks.back.cart.application;

import com.starbucks.back.cart.domain.Cart;
import com.starbucks.back.cart.dto.in.RequestAddCartDto;
import com.starbucks.back.cart.dto.in.RequestUpdateCartCountDto;
import com.starbucks.back.cart.dto.out.ResponseCartDto;
import com.starbucks.back.cart.infrastructure.CartRepository;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    /**
     * 장바구니 조회
     */
    @Transactional
    @Override
    public ResponseCartDto getCartByUserUuid(String userUuid) {
        Cart cart = cartRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        return ResponseCartDto.from(cart);
    }

    /**
     * 장바구니 겹치는지 확인
     */
    @Transactional
    @Override
    public void addCart(RequestAddCartDto requestAddCartDto) {
        if (cartRepository.existsByUserUuidAndProductOptionListUuid(
                requestAddCartDto.getUserUuid(),
                requestAddCartDto.getProductOptionListUuid()
        )) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_OPTION);
        }
        cartRepository.save(requestAddCartDto.toEntity());
    }

    /**
     * 장바구니 수량 수정
     */
    @Transactional
    @Override
    public void updateCart(RequestUpdateCartCountDto requestUpdateCartCountDto) {
        Cart cart = cartRepository.findByCartUuid(requestUpdateCartCountDto.getCartUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        // 수량 검증
        if (requestUpdateCartCountDto.getProductQuantity() < 1) {
            throw new BaseException(BaseResponseStatus.INVALID_CART_QUANTITY);
        }
        cartRepository.save(requestUpdateCartCountDto.updateCart(cart));
    }
}
