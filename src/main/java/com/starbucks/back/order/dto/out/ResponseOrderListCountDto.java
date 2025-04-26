package com.starbucks.back.order.dto.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseOrderListCountDto {
    private Integer count;

    @Builder
    public ResponseOrderListCountDto(Integer count) {
        this.count = count;
    }

    public static ResponseOrderListCountDto from(Integer count) {
        return ResponseOrderListCountDto.builder()
                .count(count)
                .build();
    }
}
