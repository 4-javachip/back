package com.starbucks.back.order.application;

import com.starbucks.back.order.dto.in.OrderItemDto;
import com.starbucks.back.order.dto.in.RequestAddOrderDetailDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderDetailDto;
import com.starbucks.back.order.vo.out.ResponseReadOrderDetailVo;

import java.util.List;

public interface OrderDetailService {

    void addOrderDetail(OrderItemDto orderItemDto);
    List<ResponseReadOrderDetailDto> getOrderDetailByOrderListUuid(String orderListUuid);
}