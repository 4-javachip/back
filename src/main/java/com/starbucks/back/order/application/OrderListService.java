package com.starbucks.back.order.application;

import com.starbucks.back.order.dto.in.RequestAddOrderListDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderListDto;

import java.util.List;

public interface OrderListService {
    void addOrderList(RequestAddOrderListDto requestAddOrderListDto);
    List<ResponseReadOrderListDto> getAllOrderList(String userUuid);
}
