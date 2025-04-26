package com.starbucks.back.order.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseOrderListCountVo {
    private Integer count;

    @Builder
    public ResponseOrderListCountVo(Integer count) {
        this.count = count;
    }

    public static ResponseOrderListCountVo from(Integer count) {
        return ResponseOrderListCountVo.builder()
                .count(count)
                .build();
    }
}
