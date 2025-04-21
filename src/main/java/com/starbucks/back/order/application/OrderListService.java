package com.starbucks.back.order.application;

import com.starbucks.back.order.dto.in.RequestAddOrderListDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderListDto;
import com.starbucks.back.order.vo.out.ResponseAddOrderListVo;
import com.starbucks.back.order.vo.out.ResponseRecentOrderListVo;

import java.util.List;

public interface OrderListService {
    ResponseAddOrderListVo addOrderList(RequestAddOrderListDto requestAddOrderListDto);
    void updateOrderList(String userUuid, String orderListUuid);
    List<ResponseReadOrderListDto> getAllOrderList(String userUuid);
    ResponseRecentOrderListVo getRecentOrderList(String userUuid);
}
