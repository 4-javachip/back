package com.starbucks.back.event.dto.in;

import com.starbucks.back.event.vo.in.RequestDeleteEventProductVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteEventProductDto {

    private String productUuid;

    @Builder
    public RequestDeleteEventProductDto(String productUuid) {
        this.productUuid = productUuid;
    }

    public static RequestDeleteEventProductDto of(RequestDeleteEventProductVo requestDeleteEventProductVo) {
        return RequestDeleteEventProductDto.builder()
                .productUuid(requestDeleteEventProductVo.getProductUuid())
                .build();
    }

}
