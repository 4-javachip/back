package com.starbucks.back.order.infrastructure;

import com.starbucks.back.order.dto.out.ResponseOrderDetailByCartUuidDto;

public interface OrderDetailCustomRepository {
    ResponseOrderDetailByCartUuidDto getOrderDetailFromCartList(String cartUuid, String orderListUuid);
}
