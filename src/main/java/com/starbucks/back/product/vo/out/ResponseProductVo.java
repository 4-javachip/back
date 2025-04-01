package com.starbucks.back.product.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseProductVo {

    private String productUuid;
    private String name;

    @Builder
    public ResponseProductVo(String productUuid, String name) {
        this.productUuid = productUuid;
        this.name = name;
    }

}
