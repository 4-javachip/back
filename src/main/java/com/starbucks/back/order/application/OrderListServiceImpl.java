package com.starbucks.back.order.application;

import com.starbucks.back.cart.application.CartService;
import com.starbucks.back.cart.dto.in.RequestDeleteCartDto;
import com.starbucks.back.cart.dto.out.ResponseCartDto;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.order.domain.OrderDetail;
import com.starbucks.back.order.domain.OrderList;
import com.starbucks.back.order.dto.in.RequestAddOrderListDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderListDto;
import com.starbucks.back.order.infrastructure.OrderListRepository;
import com.starbucks.back.product.application.ProductOptionService;
import com.starbucks.back.product.application.ProductService;
import com.starbucks.back.product.domain.ProductOption;
import com.starbucks.back.product.dto.in.RequestUpdateProductOptionDto;
import com.starbucks.back.product.dto.out.ResponseProductOptionDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderListServiceImpl implements OrderListService {
    private final OrderListRepository orderListRepository;
    private final OrderDetailService orderDetailService;
    private final ProductOptionService productOptionService;
    private final CartService cartService;

    /**
     * 주문 생성 (결제 성공 후 생성
     */
    @Transactional
    @Override
    public void addOrderList(RequestAddOrderListDto requestAddOrderListDto) {
        // 장바구니 uuid들로 장바구니 정보 조회
        List<ResponseCartDto> cartDtoList = cartService
                .getCartListByCartUuidList(requestAddOrderListDto.getOrderItemUuids());
        if (cartDtoList.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ITEM);
        }

        // OrderList 생성
        OrderList orderList = orderListRepository.save(requestAddOrderListDto.toEntity());

        // OrderDetail에 상품 정보 추가 (장바구니List에서 cartUuid 받아와서, QueryDSL로 OrderDetail 생성)

        for (ResponseCartDto responseCartDto : cartDtoList) {
            // orderDetail 에서 save 로직 작성
            orderDetailService.addOrderDetail(
                    responseCartDto.getCartUuid(),
                    orderList.getOrderListUuid()
            );

            // 재고 감소시키기
            ResponseProductOptionDto responseProductOptionDto = productOptionService
                    .getProductOptionByProductOptionUuid(responseCartDto.getProductOptionUuid());

            productOptionService.updateProductOption(
                    RequestUpdateProductOptionDto.builder()
                            .productOptionUuid(responseProductOptionDto.getProductOptionUuid())
                            .productUuid(responseProductOptionDto.getProductUuid())
                            .colorOptionId(responseProductOptionDto.getColorOptionId())
                            .sizeOptionId(responseProductOptionDto.getSizeOptionId())
                            .stock(responseProductOptionDto.getStock() - responseCartDto.getProductQuantity()) // 재고 감소
                            .price(responseProductOptionDto.getPrice())
                            .discountRate(responseProductOptionDto.getDiscountRate())
                            .build()
                    );

        }

        // cartList의 항목들 삭제
        for (ResponseCartDto responseCartDto : cartDtoList) {
            cartService.deleteCart(RequestDeleteCartDto.from(
                    requestAddOrderListDto.getUserUuid(),
                    responseCartDto.getCartUuid()
                    ));
        }

    }

    /**
     * 주문 내역 조회
     */
    @Override
    public List<ResponseReadOrderListDto> getAllOrderList(String userUuid) {
        return orderListRepository.findAllByUserUuid(userUuid)
                .stream()
                .map(ResponseReadOrderListDto::from)
                .toList();

    }

}
