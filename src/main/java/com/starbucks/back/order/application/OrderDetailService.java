package com.starbucks.back.order.application;

import com.starbucks.back.order.dto.in.RequestAddOrderDetailDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderDetailDto;
import com.starbucks.back.order.vo.out.ResponseReadOrderDetailVo;

import java.util.List;

public interface OrderDetailService {

    void addOrderDetail(String cartUuid, String OrderListUuid);
    List<ResponseReadOrderDetailDto> getOrderDetailByOrderListUuid(String orderListUuid);
}