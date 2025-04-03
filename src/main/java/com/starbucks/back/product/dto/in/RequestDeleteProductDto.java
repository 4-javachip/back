package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.vo.in.RequestDeleteProductVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteProductDto {

    private String productUuid;

    @Builder
    public RequestDeleteProductDto(String productUuid) {
        this.productUuid = productUuid;
    }

    public static RequestDeleteProductDto from(RequestDeleteProductVo requestDeleteProductVo) {
        return RequestDeleteProductDto.builder()
                .productUuid(requestDeleteProductVo.getProductUuid())
                .build();
    }

}
