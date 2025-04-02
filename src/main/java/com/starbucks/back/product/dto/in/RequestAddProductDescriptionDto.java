package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.domain.ProductDescription;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddProductDescriptionDto {

    String productUuid;
    String description;
    String detailDescription;

    @Builder
    public RequestAddProductDescriptionDto(String productUuid, String description, String detailDescription) {
        this.productUuid = productUuid;
        this.description = description;
        this.detailDescription = detailDescription;
    }

    public ProductDescription toEntity() {
        return ProductDescription.builder()
                .productUuid(productUuid)
                .description(description)
                .detailDescription(detailDescription)
                .build();
    }

}
