package com.starbucks.back.order.application;

import com.starbucks.back.order.dto.in.RequestAddOrderDetailDto;

public interface OrderDetailService {

    void addOrderDetail(String cartUuid, String OrderListUuid);
}
