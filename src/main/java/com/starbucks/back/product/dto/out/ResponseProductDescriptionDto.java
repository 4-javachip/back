package com.starbucks.back.product.dto.out;

import com.starbucks.back.product.domain.ProductDescription;
import com.starbucks.back.product.vo.out.ResponseProductDescriptionVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseProductDescriptionDto {

    private String productUuid;
    private String description;
    private String detailDescription;

    @Builder
    public ResponseProductDescriptionDto(String productUuid, String description, String detailDescription) {
        this.productUuid = productUuid;
        this.description = description;
        this.detailDescription = detailDescription;
    }

    public static ResponseProductDescriptionDto from(ProductDescription productDescription) {
        return ResponseProductDescriptionDto.builder()
                .productUuid(productDescription.getProductUuid())
                .description(productDescription.getDescription())
                .detailDescription(productDescription.getDetailDescription())
                .build();
    }

    public ResponseProductDescriptionVo toVo() {
        return ResponseProductDescriptionVo.builder()
                .productUuid(productUuid)
                .description(description)
                .detailDescription(detailDescription)
                .build();
    }

}
