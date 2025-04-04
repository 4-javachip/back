package com.starbucks.back.best.dto.in;

import com.starbucks.back.best.domain.Best;
import com.starbucks.back.best.vo.in.RequestBestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateBestDto {

    private String productUuid;
    private Integer productSalesCount;

    @Builder
    public RequestUpdateBestDto(String productUuid, Integer productSalesCount) {
        this.productUuid = productUuid;
        this.productSalesCount = productSalesCount;
    }

    public Best updateEntity(Best best)  {
        return Best.builder()
                .id(best.getId())
                .productUuid(productUuid)
                .productSalesCount(productSalesCount)
                .build();
    }

    public static RequestUpdateBestDto from(RequestBestVo requestBestVo) {
        return RequestUpdateBestDto.builder()
                .productUuid(requestBestVo.getProductUuid())
                .productSalesCount(requestBestVo.getProductSalesCount())
                .build();
    }

}
