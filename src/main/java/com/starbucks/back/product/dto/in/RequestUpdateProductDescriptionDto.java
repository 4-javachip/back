package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.domain.ProductDescription;
import com.starbucks.back.product.vo.in.RequestProductDescriptionVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateProductDescriptionDto {

    private String productUuid;
    private String description;
    private String detailDescription;

    @Builder
    public RequestUpdateProductDescriptionDto(String productUuid, String description, String detailDescription) {
        this.productUuid = productUuid;
        this.description = description;
        this.detailDescription = detailDescription;
    }

    public ProductDescription updateEntity(ProductDescription productDescription) {
        return ProductDescription.builder()
                .id(productDescription.getId())
                .productUuid(productUuid)
                .description(description)
                .detailDescription(detailDescription)
                .build();
    }

    public static RequestUpdateProductDescriptionDto from(RequestProductDescriptionVo requestProductDescriptionVo) {
        return RequestUpdateProductDescriptionDto.builder()
                .productUuid(requestProductDescriptionVo.getProductUuid())
                .description(requestProductDescriptionVo.getDescription())
                .detailDescription(requestProductDescriptionVo.getDetailDescription())
                .build();
    }

}
