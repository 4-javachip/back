package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.domain.ProductCategoryList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddProductCategoryListDto {

    private String productUuid;
    private Long categoryId;
    private Long subCategoryId;

    @Builder
    public RequestAddProductCategoryListDto(String productUuid, Long categoryId, Long subCategoryId) {
        this.productUuid = productUuid;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
    }

    public ProductCategoryList toEntity() {
        return ProductCategoryList.builder()
                .productUuid(productUuid)
                .categoryId(categoryId)
                .subCategoryId(subCategoryId)
                .build();
    }

}
