package com.starbucks.back.order.application;

import com.starbucks.back.order.dto.in.RequestAddOrderListDto;

public interface OrderListService {
    void addOrderList(RequestAddOrderListDto requestAddOrderListDto);
}
