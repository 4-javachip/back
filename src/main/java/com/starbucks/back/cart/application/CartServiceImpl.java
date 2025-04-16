package com.starbucks.back.cart.application;

import com.starbucks.back.cart.domain.Cart;
import com.starbucks.back.cart.dto.in.RequestAddCartDto;
import com.starbucks.back.cart.dto.in.RequestDeleteCartDto;
import com.starbucks.back.cart.dto.in.RequestUpdateCartCheckedDto;
import com.starbucks.back.cart.dto.in.RequestUpdateCartCountDto;
import com.starbucks.back.cart.dto.out.ResponseCartDto;
import com.starbucks.back.cart.infrastructure.CartRepository;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.product.application.ProductOptionService;
import com.starbucks.back.product.application.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final ProductOptionService productOptionService;
    /**
     * 장바구니 조회 by userUuid
     */
    @Transactional
    @Override
    public List<ResponseCartDto> getCartListByUserUuid(String userUuid) {
        return cartRepository.findAllByUserUuidAndDeletedFalse(userUuid)
                .stream()
                .map(ResponseCartDto::from)
                .toList();
    }

    /**
     * 장바구니 생성 by userUuid, productUuid, productOptionUuid
     */
    @Transactional
    @Override
    public void addCart(RequestAddCartDto requestAddCartDto) {
        // productUuid 존재하지 않으면, getProductByProductUuid에서 예외 발생
        productService.getProductByUuid(requestAddCartDto.getProductUuid());

        // productOptionUuid 존재하지 않으면, getProductOptionByProductOptionUuid에서 예외 발생
        productOptionService.getProductOptionByProductOptionUuid(requestAddCartDto.getProductOptionUuid());

        // 장바구니 중복 검사
        if (cartRepository.existsByUserUuidAndProductOptionUuidAndDeletedFalse(
                requestAddCartDto.getUserUuid(),
                requestAddCartDto.getProductOptionUuid()
        )) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_CART_PRODUCT);
        }
        cartRepository.save(requestAddCartDto.toEntity());
    }

    /**
     * 장바구니 수량 수정 by userUuid, cartUuid, productQuantity
     */
    @Transactional
    @Override
    public void updateCart(RequestUpdateCartCountDto requestUpdateCartCountDto) {
        // 수량 검증
        if (requestUpdateCartCountDto.getProductQuantity() < 1) {
            throw new BaseException(BaseResponseStatus.INVALID_CART_QUANTITY);
        }
        Cart cart = cartRepository.findByUserUuidAndCartUuid(
                    requestUpdateCartCountDto.getUserUuid(),
                    requestUpdateCartCountDto.getCartUuid()
                )
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CART_PRODUCT));
        cartRepository.save(requestUpdateCartCountDto.updateCart(cart));
    }

    /**
     * 장바구니 체크박스 수정 by userUuid, cartUuid
     */
    @Transactional
    @Override
    public void updateCartChecked(RequestUpdateCartCheckedDto requestUpdateCartCheckedDto) {
        Cart cart = cartRepository.findByUserUuidAndCartUuid(
                requestUpdateCartCheckedDto.getUserUuid(),
                requestUpdateCartCheckedDto.getCartUuid()
                )
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CART_PRODUCT));
        cartRepository.save(requestUpdateCartCheckedDto.updateCart(cart));
    }

    /**
     * 장바구니 삭제
     */
    @Transactional
    @Override
    public void deleteCart(RequestDeleteCartDto requestDeleteCartDto) {
        Cart cart = cartRepository.findByUserUuidAndCartUuid(
                requestDeleteCartDto.getUserUuid(),
                requestDeleteCartDto.getCartUuid()
                )
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CART_PRODUCT));
        cart.softDelete();
    }
}
