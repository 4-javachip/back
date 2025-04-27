package com.starbucks.back.order.infrastructure;

import com.starbucks.back.order.dto.in.OrderItemDto;
import com.starbucks.back.order.dto.out.ResponseOrderDetailByOrderItemDto;

public interface OrderDetailCustomRepository {
    ResponseOrderDetailByOrderItemDto getOrderDetailFromOrderItem(OrderItemDto orderItemDto);
}
