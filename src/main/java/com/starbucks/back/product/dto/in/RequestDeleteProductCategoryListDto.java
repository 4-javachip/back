package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.vo.in.RequestDeleteProductCategoryListVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteProductCategoryListDto {

    private String productUuid;

    @Builder
    public RequestDeleteProductCategoryListDto(String productUuid) {
        this.productUuid = productUuid;
    }

    public static RequestDeleteProductCategoryListDto of (RequestDeleteProductCategoryListVo requestDeleteProductCategoryListVo) {
        return RequestDeleteProductCategoryListDto.builder()
                .productUuid(requestDeleteProductCategoryListVo.getProductUuid())
                .build();
    }

}
