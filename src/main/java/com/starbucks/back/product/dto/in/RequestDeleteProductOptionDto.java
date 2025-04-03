package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.vo.in.RequestDeleteProductOptionVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteProductOptionDto {

    private String productOptionUuid;

    @Builder
    public RequestDeleteProductOptionDto(String productOptionUuid) {
        this.productOptionUuid = productOptionUuid;
    }

    public static RequestDeleteProductOptionDto from(RequestDeleteProductOptionVo requestDeleteProductOptionVo) {
        return RequestDeleteProductOptionDto.builder()
                .productOptionUuid(requestDeleteProductOptionVo.getProductOptionUuid())
                .build();
    }

}
