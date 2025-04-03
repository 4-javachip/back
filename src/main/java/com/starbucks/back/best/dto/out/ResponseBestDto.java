package com.starbucks.back.best.dto.out;

import com.starbucks.back.best.domain.Best;
import com.starbucks.back.best.vo.out.ResponseBestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseBestDto {

    private Long id;
    private String productUuid;
    private Integer productSalesCount;

    @Builder
    public ResponseBestDto(Long id, String productUuid, Integer productSalesCount) {
        this.id = id;
        this.productUuid = productUuid;
        this.productSalesCount = productSalesCount;
    }

    public static ResponseBestDto from(Best best) {
        return ResponseBestDto.builder()
                .id(best.getId())
                .productUuid(best.getProductUuid())
                .productSalesCount(best.getProductSalesCount())
                .build();
    }

    public ResponseBestVo toVo() {
        return ResponseBestVo.builder()
                .id(id)
                .productUuid(productUuid)
                .productSalesCount(productSalesCount)
                .build();
    }
}
