package com.starbucks.back.recent.dto.out;

import com.starbucks.back.recent.vo.out.ResponseGetRecentViewedProductVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseGetRecentViewedProductDto {
    private int index;
    private String productUuid;

    @Builder
    public ResponseGetRecentViewedProductDto(int index, String productUuid) {
        this.index = index;
        this.productUuid = productUuid;
    }

    public static ResponseGetRecentViewedProductDto from(int index, String productUuid) {
        return ResponseGetRecentViewedProductDto.builder()
                .index(index)
                .productUuid(productUuid)
                .build();
    }

    public ResponseGetRecentViewedProductVo toVo() {
        return ResponseGetRecentViewedProductVo.builder()
                .index(index)
                .productUuid(productUuid)
                .build();
    }
}
