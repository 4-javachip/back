package com.starbucks.back.best.dto.in;

import com.starbucks.back.best.domain.Best;
import com.starbucks.back.best.vo.in.RequestAddBestVo;
import com.starbucks.back.best.vo.in.RequestBestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddBestDto {

    private String productUuid;
    private Integer productSalesCount;

    @Builder
    public RequestAddBestDto(String productUuid, Integer productSalesCount) {
        this.productUuid = productUuid;
        this.productSalesCount = productSalesCount;
    }

    public Best toEntity() {
        return Best.builder()
                .productUuid(productUuid)
                .productSalesCount(productSalesCount)
                .build();
    }

    public static RequestAddBestDto from(RequestAddBestVo requestAddBestVo) {
        return RequestAddBestDto.builder()
                .productUuid(requestAddBestVo.getProductUuid())
                .productSalesCount(requestAddBestVo.getProductSalesCount())
                .build();
    }

}
