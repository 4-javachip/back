package com.starbucks.back.best.dto.in;

import com.starbucks.back.best.vo.in.RequestDeleteBestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteBestDto {

    private String productUuid;

    @Builder
    public RequestDeleteBestDto(String productUuid) {
        this.productUuid = productUuid;
    }

    public static RequestDeleteBestDto of(RequestDeleteBestVo requestDeleteBestVo) {
        return RequestDeleteBestDto.builder()
                .productUuid(requestDeleteBestVo.getProductUuid())
                .build();
    }

}
