package com.starbucks.back.order.presentation;

import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.order.application.OrderListService;
import com.starbucks.back.order.dto.in.RequestAddOrderListDto;
import com.starbucks.back.order.vo.in.RequestAddOrderListVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderListService orderListService;
    private final SecurityUtil securityUtil;

    /**
     * 주문 생성
     */
    @PostMapping
    @Operation(summary = "CreateOrder API", description = "주문 생성 API 입니다.", tags = {"Order-Service"})
    public void addOrder(@RequestBody RequestAddOrderListVo requestAddOrderListVo) {
        String userUuid = securityUtil.getCurrentUserUuid();

        RequestAddOrderListDto requestAddOrderListDto = RequestAddOrderListDto.from(userUuid, requestAddOrderListVo);

        orderListService.addOrderList(requestAddOrderListDto);

    }

}
