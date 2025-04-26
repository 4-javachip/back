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
import com.starbucks.back.order.dto.out.ResponseOrderListCountDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderDetailDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderListDto;
import com.starbucks.back.order.infrastructure.OrderListRepository;
import com.starbucks.back.order.vo.in.OrderItemVo;
import com.starbucks.back.order.vo.out.RecentOrderItemVo;
import com.starbucks.back.order.vo.out.ResponseAddOrderListVo;
import com.starbucks.back.order.vo.out.ResponseRecentOrderListVo;
import com.starbucks.back.payment.dto.in.UpdateOrderDto;
import com.starbucks.back.product.application.ProductOptionService;
import com.starbucks.back.product.dto.in.RequestUpdateProductOptionDto;
import com.starbucks.back.product.dto.out.ResponseProductOptionDto;
import com.starbucks.back.shippingaddress.application.ShippingAddressService;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressWithDefaultedDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderListServiceImpl implements OrderListService {
    private final OrderListRepository orderListRepository;
    private final OrderDetailService orderDetailService;
    private final ProductOptionService productOptionService;
    private final ShippingAddressService shippingAddressService;
    private final CartService cartService;
    private final BestService bestService;

    /**
     * 주문 생성
     */
    @Transactional
    @Override
    public ResponseAddOrderListVo addOrderList(RequestAddOrderListDto requestAddOrderListDto) {

        // 주문 상품이 없으면 에러
        if (requestAddOrderListDto.getOrderItems().isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ITEM);
        }
        // OrderList 생성
        ResponseReadShippingAddressWithDefaultedDto responseReadShippingAddressWithDefaultedDto =
                shippingAddressService.getShippingAddressByShippingAddressUuid(
                        requestAddOrderListDto.getShippingAddressUuid()
                );

        OrderList orderList = orderListRepository.save(
                requestAddOrderListDto.toEntity(
                        responseReadShippingAddressWithDefaultedDto
                )
        );

        // OrderDetail에 상품 정보 추가
        for (OrderItemVo orderItemVo : requestAddOrderListDto.getOrderItems()) {
            OrderItemDto orderItemDto = OrderItemDto.from(
                    orderList.getOrderListUuid(),
                    orderItemVo
            );
            // orderDetail 에서 save 로직 작성
            ResponseOrderDetailByOrderItemDto responseOrderDetailByOrderItemDto =
                    orderDetailService.addOrderDetail(orderItemDto);
        }

        return ResponseAddOrderListVo.builder()
                .orderListUuid(orderList.getOrderListUuid())
                .build();
    }

    /**
     * 주문 내역 수정
     */
    @Override
    public void updateOrderList (UpdateOrderDto updateOrderDto) {

        OrderList orderList = orderListRepository.findByOrderListUuid(updateOrderDto.getOrderListUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_ORDER_LIST));
        List<ResponseReadOrderDetailDto> responseReadOrderDetailDtos = orderDetailService
                .getOrderDetailByOrderListUuid(updateOrderDto.getOrderListUuid());

        // 장바구니에서 조회면, 해당 장바구니를 삭제
        if (orderList.getFromCart()) {
            for (ResponseReadOrderDetailDto responseReadOrderDetailDto : responseReadOrderDetailDtos) {
                cartService.deleteCartByUserUuidAndProductOptionUuid(
                        updateOrderDto.getUserUuid(),
                        responseReadOrderDetailDto.getProductOptionUuid()
                );

            }
        }

        // orderList 테이블 수정
        orderListRepository.updateOrderListStatus(
                updateOrderDto.getOrderListUuid(),
                updateOrderDto.getPaymentUuid(),
                updateOrderDto.getOrderName(),
                updateOrderDto.getMethod(),
                PaymentStatus.from(updateOrderDto.getPaymentStatus())
        );

        // for문 시작
        for (ResponseReadOrderDetailDto responseReadOrderDetailDto : responseReadOrderDetailDtos) {
            ResponseProductOptionDto responseProductOptionDto = productOptionService
                    .getProductOptionByProductOptionUuid(responseReadOrderDetailDto.getProductOptionUuid());
            productOptionService.updateProductOption(
                    RequestUpdateProductOptionDto.builder()
                            .productOptionUuid(responseProductOptionDto.getProductOptionUuid())
                            .productUuid(responseProductOptionDto.getProductUuid())
                            .colorOptionId(responseProductOptionDto.getColorOptionId())
                            .sizeOptionId(responseProductOptionDto.getSizeOptionId())
                            .stock(responseProductOptionDto.getStock() - responseReadOrderDetailDto.getQuantity()) // 재고 감소
                            .price(responseProductOptionDto.getPrice())
                            .discountRate(responseProductOptionDto.getDiscountRate())
                            .build()
            );

            // Best 테이블에 판매량 추가
            // (productUuid로 상품 찾고, 있으면 판매량 +, 없으면 생성)
            bestService.increaseBestProductSalesCount(
                    responseProductOptionDto.getProductUuid(), responseReadOrderDetailDto.getQuantity()
            );
        }
    }

    /**
     * 주문 내역 조회
     */
    @Override
    public List<ResponseReadOrderListDto> getAllOrderList(String userUuid) {
        return orderListRepository.findAllByUserUuidAndPaymentStatus(userUuid, PaymentStatus.DONE)
                .stream()
                .map(ResponseReadOrderListDto::from)
                .toList();

    }

    /**
     * 최근 주문 내역 조회
     */
    @Override
    public ResponseRecentOrderListVo getRecentOrderList(String userUuid) {

        OrderList orderList =  orderListRepository.findTopByUserUuidAndPaymentStatusOrderByCreatedAtDesc(userUuid, PaymentStatus.DONE)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_ORDER_LIST));

        List<RecentOrderItemVo> recentOrderItemVos = new ArrayList<>();

        // 주문한 상품들 (order items) 목록 생성
        List<ResponseReadOrderDetailDto> responseReadOrderDetailDtos = orderDetailService
                .getOrderDetailByOrderListUuid(orderList.getOrderListUuid());

        for (ResponseReadOrderDetailDto responseReadOrderDetailDto : responseReadOrderDetailDtos) {
            recentOrderItemVos.add(RecentOrderItemVo.from(responseReadOrderDetailDto));
        }

        return ResponseRecentOrderListVo.from(orderList, recentOrderItemVos);
    }

    /**
     * 주문 내역 존재 여부
     */
    @Override
    public Boolean existsOrderByUserUuidAndProductUuid(String userUuid, String productUuid) {
        return orderListRepository.existsOrderByUserUuidAndProductUuid(userUuid, productUuid);
    }

    /**
     * 주문 내역 개수 조회
     */
    @Override
    public ResponseOrderListCountDto getOrderListCount(String userUuid) {
        Integer result = orderListRepository.countByUserUuidAndPaymentStatus(userUuid, PaymentStatus.DONE);
        return ResponseOrderListCountDto.from(result);
    }
}
