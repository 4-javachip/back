package com.starbucks.back.product.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseProductDescriptionVo {

    private String productUuid;
    private String description;
    private String detailDescription;

    @Builder
    public ResponseProductDescriptionVo(String productUuid, String description, String detailDescription) {
        this.productUuid = productUuid;
        this.description = description;
        this.detailDescription = detailDescription;
    }

}
