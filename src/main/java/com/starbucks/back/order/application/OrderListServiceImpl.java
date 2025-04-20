package com.starbucks.back.order.application;

import com.starbucks.back.best.application.BestService;
import com.starbucks.back.cart.application.CartService;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.order.domain.OrderList;
import com.starbucks.back.order.domain.enums.PaymentStatus;
import com.starbucks.back.order.dto.in.OrderItemDto;
import com.starbucks.back.order.dto.in.RequestAddOrderListDto;
import com.starbucks.back.order.dto.out.ResponseOrderDetailByOrderItemDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderListDto;
import com.starbucks.back.order.infrastructure.OrderListRepository;
import com.starbucks.back.order.vo.in.OrderItemVo;
import com.starbucks.back.order.vo.out.AddedOrderItemVo;
import com.starbucks.back.order.vo.out.ResponseAddOrderListVo;
import com.starbucks.back.payment.application.PaymentService;
import com.starbucks.back.payment.dto.out.ResponsePaymentDto;
import com.starbucks.back.product.application.ProductOptionService;
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
    private final PaymentService paymentService;
    private final CartService cartService;
    private final BestService bestService;

    /**
     * 주문 생성 (결제 성공 후 생성
     */
    @Transactional
    @Override
    public ResponseAddOrderListVo addOrderList(RequestAddOrderListDto requestAddOrderListDto) {



        // 장바구니에서 조회면, 해당 장바구니를 삭제
        if (requestAddOrderListDto.getFromCart()) {
            for (OrderItemVo orderItemVo : requestAddOrderListDto.getOrderItems()) {
                cartService.deleteCartByUserUuidAndProductOptionUuid(
                        requestAddOrderListDto.getUserUuid(),
                        orderItemVo.getProductOptionUuid()
                );
            }
        }

        // 주문 상품이 없으면 에러
        if (requestAddOrderListDto.getOrderItems().isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ITEM);
        }

        // OrderList 생성
        ResponsePaymentDto responsePaymentDto = paymentService.getPayment(requestAddOrderListDto.getPaymentUuid());
        OrderList orderList = orderListRepository.save(
                requestAddOrderListDto.toEntity(PaymentStatus
                        .from(responsePaymentDto.getPaymentStatus().getDescription())
                )
        );

        // OrderDetail에 상품 정보 추가 (장바구니List에서 cartUuid 받아와서, QueryDSL로 OrderDetail 생성)

        for (OrderItemVo orderItemVo : requestAddOrderListDto.getOrderItems()) {
            OrderItemDto orderItemDto = OrderItemDto.from(
                    orderList.getOrderListUuid(),
                    orderItemVo
            );
            // orderDetail 에서 save 로직 작성
            ResponseOrderDetailByOrderItemDto responseOrderDetailByOrderItemDto =
                    orderDetailService.addOrderDetail(orderItemDto);

            // 재고 감소시키기
            ResponseProductOptionDto responseProductOptionDto = productOptionService
                    .getProductOptionByProductOptionUuid(orderItemVo.getProductOptionUuid());

            productOptionService.updateProductOption(
                    RequestUpdateProductOptionDto.builder()
                            .productOptionUuid(responseProductOptionDto.getProductOptionUuid())
                            .productUuid(responseProductOptionDto.getProductUuid())
                            .colorOptionId(responseProductOptionDto.getColorOptionId())
                            .sizeOptionId(responseProductOptionDto.getSizeOptionId())
                            .stock(responseProductOptionDto.getStock() - orderItemVo.getQuantity()) // 재고 감소
                            .price(responseProductOptionDto.getPrice())
                            .discountRate(responseProductOptionDto.getDiscountRate())
                            .build()
                    );
            log.info("재고 감소 성공, productOptionUuid: {}, stock: {}",
                    responseProductOptionDto.getProductOptionUuid(),
                    responseProductOptionDto.getStock() - orderItemVo.getQuantity()
            );

            // Best 테이블에 판매량 추가
            // (productUuid로 상품 찾고, 있으면 판매량 +, 없으면 생성)
            bestService.increaseBestProductSalesCount(
                    responseProductOptionDto.getProductUuid(), orderItemVo.getQuantity()
            );

            // ResponseVo에 담을 상품정보 추가
//            addedOrderItemVos.add(
//                    AddedOrderItemVo.builder()
//                            .name(responseOrderDetailByOrderItemDto.getName())
//                            .quantity(responseOrderDetailByOrderItemDto.getQuantity())
//                            .thumbnail(responseOrderDetailByOrderItemDto.getThumbnail())
//                            .sizeName()
//            )
        }
        String shippingAddressUuid = requestAddOrderListDto.getShippingAddressUuid();
        String orderListUuid = orderList.getOrderListUuid();
        com.starbucks.back.payment.domain.PaymentStatus paymentStatus = responsePaymentDto.getPaymentStatus();
        Integer totalOriginPrice = responsePaymentDto.getTotalOriginPrice();
        Integer totalPurchasePrice = responsePaymentDto.getTotalPurchasePrice();
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
