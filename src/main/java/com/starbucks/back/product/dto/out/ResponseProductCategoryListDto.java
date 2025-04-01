package com.starbucks.back.product.dto.out;

import com.starbucks.back.product.domain.ProductCategoryList;
import com.starbucks.back.product.vo.out.ResponseProductCategoryListVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseProductCategoryListDto {

    private String productUuid;
    private Long categoryId;
    private Long subCategoryId;

    @Builder
    public ResponseProductCategoryListDto(String productUuid, Long categoryId, Long subCategoryId) {
        this.productUuid = productUuid;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
    }

    public static ResponseProductCategoryListDto from(ProductCategoryList productCategoryList) {
        return ResponseProductCategoryListDto.builder()
                .productUuid(productCategoryList.getProductUuid())
                .categoryId(productCategoryList.getCategoryId())
                .subCategoryId(productCategoryList.getSubCategoryId())
                .build();
    }

    public ResponseProductCategoryListVo toVo() {
        return ResponseProductCategoryListVo.builder()
                .productUuid(productUuid)
                .categoryId(categoryId)
                .subCategoryId(subCategoryId)
                .build();
    }

}
