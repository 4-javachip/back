package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.vo.in.RequestDeleteProductDescriptionVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteProductDescriptionDto {

    private String productUuid;

    @Builder
    public RequestDeleteProductDescriptionDto(String productUuid) {
        this.productUuid = productUuid;
    }

    public static RequestDeleteProductDescriptionDto from(RequestDeleteProductDescriptionVo requestDeleteProductDescriptionVo) {
        return RequestDeleteProductDescriptionDto.builder()
                .productUuid(requestDeleteProductDescriptionVo.getProductUuid())
                .build();
    }

}
