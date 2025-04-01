package com.starbucks.back.cart.application;

import com.starbucks.back.cart.domain.Cart;
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
}
