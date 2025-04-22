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
import com.starbucks.back.order.dto.out.ResponseReadOrderDetailDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderListDto;
import com.starbucks.back.order.infrastructure.OrderListRepository;
import com.starbucks.back.order.vo.in.OrderItemVo;
import com.starbucks.back.order.vo.out.RecentOrderItemVo;
import com.starbucks.back.order.vo.out.ResponseAddOrderListVo;
import com.starbucks.back.order.vo.out.ResponseRecentOrderListVo;
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
     * 주문 생성 (결제 성공 후 생성
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
            log.info("orderItemDto@@: {}", orderItemDto.toString());
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
    public void updateOrderList (String userUuid, String orderListUuid, String orderStatus) {

        log.info("userUuid@@, orderListUuid: {}", orderListUuid);
        OrderList orderList = orderListRepository.findByOrderListUuid(orderListUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_ORDER_LIST));
        log.info("orderList@@: {}", orderList);
        List<ResponseReadOrderDetailDto> responseReadOrderDetailDtos = orderDetailService
                .getOrderDetailByOrderListUuid(orderListUuid);

        // 장바구니에서 조회면, 해당 장바구니를 삭제
        if (orderList.getFromCart()) {
            for (ResponseReadOrderDetailDto responseReadOrderDetailDto : responseReadOrderDetailDtos) {
                cartService.deleteCartByUserUuidAndProductOptionUuid(
                        userUuid,
                        responseReadOrderDetailDto.getProductOptionUuid()
                );
            }
        }

        // orderList 테이블 수정
        orderListRepository.updateOrderListStatus(
                orderListUuid,
                PaymentStatus.from(orderStatus)
        );

        // for문 시작
        for (ResponseReadOrderDetailDto responseReadOrderDetailDto : responseReadOrderDetailDtos) {
            log.info("responseReadOrderDetailDto@@: {}", responseReadOrderDetailDto.getProductOptionUuid());
            ResponseProductOptionDto responseProductOptionDto = productOptionService
                    .getProductOptionByProductOptionUuid(responseReadOrderDetailDto.getProductOptionUuid());
            log.info("responseProductOptionDto@@: {}", responseProductOptionDto);
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
            log.info("재고 감소 성공, productOptionUuid: {}, stock: {}",
                    responseProductOptionDto.getProductOptionUuid(),
                    responseProductOptionDto.getStock() - responseReadOrderDetailDto.getQuantity()
            );

            // Best 테이블에 판매량 추가
            // (productUuid로 상품 찾고, 있으면 판매량 +, 없으면 생성)
            log.info("responseProductOptionDto.getProductUuid(), responseReadOrderDetailDto.getQuantity()" +
                    " : {}, {}", responseProductOptionDto.getProductUuid(), responseReadOrderDetailDto.getQuantity());
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

        OrderList orderList =  orderListRepository.findTopByUserUuidOrderByCreatedAtDesc(userUuid)
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
}
