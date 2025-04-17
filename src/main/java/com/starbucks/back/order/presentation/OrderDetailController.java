package com.starbucks.back.order.presentation;

import com.starbucks.back.cart.application.CartService;
import com.starbucks.back.cart.dto.out.ResponseCartDto;
import com.starbucks.back.cart.vo.out.ResponseCartVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.order.application.OrderDetailService;
import com.starbucks.back.order.dto.out.ResponseReadOrderDetailDto;
import com.starbucks.back.order.vo.out.ResponseReadOrderDetailVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    private final SecurityUtil securityUtil;
    private final CartService cartService;

    /**
     * 주문 상세 조회 by userUuid, orderListUuid
     */
    @GetMapping("/detail/list/{orderListUuid}")
    @Operation(summary = "GetOrderDetailByCartUuid API", description = "주문 상세 조회 api 입니다.", tags = {"Order-Service"})
    public BaseResponseEntity<List<ResponseReadOrderDetailVo>> getOrderDetail(@PathVariable("orderListUuid") String orderListUuid) {
        String userUuid = securityUtil.getCurrentUserUuid();

        List<ResponseReadOrderDetailVo> result = orderDetailService.getOrderDetailByOrderListUuid(orderListUuid)
                .stream()
                .map(ResponseReadOrderDetailDto::toVo)
                .toList();

        return new BaseResponseEntity<>(result);
    }

    /**
     * 주문 아이템 목록(장바구니) by userUuid
     */
    @GetMapping("/items")
    @Operation(summary = "GetOrderItemListByUserUuid API", description = "주문 아이템 목록 조회 api 입니다.", tags = {"Order-Service"})
    public BaseResponseEntity<List<ResponseCartVo>> getOrderItemList() {
        String userUuid = securityUtil.getCurrentUserUuid();

        List<ResponseCartVo> result = cartService.getCartCheckedListByUserUuid(userUuid)
                .stream()
                .map(ResponseCartDto::toVo)
                .toList();

        return new BaseResponseEntity<>(result);
    }
}
