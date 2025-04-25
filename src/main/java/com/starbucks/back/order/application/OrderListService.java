package com.starbucks.back.order.application;

import com.starbucks.back.order.dto.in.RequestAddOrderListDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderListDto;
import com.starbucks.back.order.vo.out.ResponseAddOrderListVo;
import com.starbucks.back.order.vo.out.ResponseRecentOrderListVo;
import com.starbucks.back.payment.dto.in.UpdateOrderDto;

import java.util.List;

public interface OrderListService {
    ResponseAddOrderListVo addOrderList(RequestAddOrderListDto requestAddOrderListDto);
    void updateOrderList(UpdateOrderDto updateOrderDto);
    List<ResponseReadOrderListDto> getAllOrderList(String userUuid);
    ResponseRecentOrderListVo getRecentOrderList(String userUuid);
    Boolean existsOrderByUserUuidAndProductUuid(String userUuid, String productUuid);
}
