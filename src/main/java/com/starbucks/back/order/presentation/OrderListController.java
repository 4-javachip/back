package com.starbucks.back.order.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.order.application.OrderListService;
import com.starbucks.back.order.dto.in.RequestAddOrderListDto;
import com.starbucks.back.order.dto.out.ResponseOrderListCountDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderListDto;
import com.starbucks.back.order.vo.in.RequestAddOrderListVo;
import com.starbucks.back.order.vo.out.ResponseAddOrderListVo;
import com.starbucks.back.order.vo.out.ResponseOrderListCountVo;
import com.starbucks.back.order.vo.out.ResponseReadOrderListVo;
import com.starbucks.back.order.vo.out.ResponseRecentOrderListVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderListController {

    private final OrderListService orderListService;
    private final SecurityUtil securityUtil;

    /**
     * 주문 생성
     */
    @PostMapping
    @Operation(summary = "주문 생성 API", description = "주문 생성 api", tags = {"Order-Service"})
    public BaseResponseEntity<ResponseAddOrderListVo> addOrder(@RequestBody RequestAddOrderListVo requestAddOrderListVo) {
        String userUuid = securityUtil.getCurrentUserUuid();

        RequestAddOrderListDto requestAddOrderListDto = RequestAddOrderListDto.from(userUuid, requestAddOrderListVo);

        ResponseAddOrderListVo result = orderListService.addOrderList(requestAddOrderListDto);

        return new BaseResponseEntity<>(result);
    }

    /**
     * 주문 내역 전체 조회 by userUuid
     */
    @GetMapping("/list")
    @Operation(summary = "주문 내역 조회 API", description = "주문 내역 조회 API 입니다.", tags = {"Order-Service"})
    public BaseResponseEntity<List<ResponseReadOrderListVo>> getOrderList() {
        String userUuid = securityUtil.getCurrentUserUuid();

        List<ResponseReadOrderListVo> result = orderListService.getAllOrderList(userUuid)
                .stream()
                .map(ResponseReadOrderListDto::toVo)
                .toList();

        return new BaseResponseEntity<>(result);
    }

    /**
     * 최근 주문 조회 by userUuid
     */
    @GetMapping("/recent")
    @Operation(summary = "최근 주문 내역 조회 API", description = "최근 주문 내역 조회 API 입니다.", tags = {"Order-Service"})
    public BaseResponseEntity<ResponseRecentOrderListVo> getRecentOrderList() {
        String userUuid = securityUtil.getCurrentUserUuid();

        ResponseRecentOrderListVo result = orderListService.getRecentOrderList(userUuid);

        return new BaseResponseEntity<>(result);
    }

    /**
     * 주문 내역 개수 조회 by userUuid
     */
    @GetMapping("/list/count")
    @Operation(summary = "주문 내역 개수 조회 API", description = "주문 내역 개수 조회 API 입니다.", tags = {"Order-Service"})
    public BaseResponseEntity<ResponseOrderListCountVo> getOrderListCount() {
        String userUuid = securityUtil.getCurrentUserUuid();

        ResponseOrderListCountDto responseOrderListCountDto = orderListService.getOrderListCount(userUuid);

        return new BaseResponseEntity<>(ResponseOrderListCountVo.from(responseOrderListCountDto.getCount()));
    }

}
