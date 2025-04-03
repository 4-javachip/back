package com.starbucks.back.product.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseProductCategoryListVo {

    private String productUuid;
    private Long categoryId;
    private Long subCategoryId;

    @Builder
    public ResponseProductCategoryListVo(String productUuid, Long categoryId, Long subCategoryId) {
        this.productUuid = productUuid;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
    }

}
