package com.starbucks.back.best.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseBestVo {

    private Long id;
    private String productUuid;
    private Integer productSalesCount;

    @Builder
    public ResponseBestVo(Long id, String productUuid, Integer productSalesCount) {
        this.id = id;
        this.productUuid = productUuid;
        this.productSalesCount = productSalesCount;
    }

}
