package com.starbucks.back.recent.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseGetRecentViewedProductVo {
    private int index;
    private String productUuid;

    @Builder
    public ResponseGetRecentViewedProductVo(int index, String productUuid) {
        this.index = index;
        this.productUuid = productUuid;
    }
}
