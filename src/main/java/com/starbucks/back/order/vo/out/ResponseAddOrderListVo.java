package com.starbucks.back.order.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseAddOrderListVo {

    private String orderListUuid;

    @Builder
    public ResponseAddOrderListVo(String orderListUuid) {
        this.orderListUuid = orderListUuid;
    }
}
