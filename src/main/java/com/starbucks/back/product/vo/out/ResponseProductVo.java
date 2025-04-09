package com.starbucks.back.product.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseProductVo {

    private String productUuid;
    private String name;
    private boolean isNew;

    @Builder
    public ResponseProductVo(String productUuid, String name, boolean isNew) {
        this.productUuid = productUuid;
        this.name = name;
        this.isNew = isNew;
    }

}
