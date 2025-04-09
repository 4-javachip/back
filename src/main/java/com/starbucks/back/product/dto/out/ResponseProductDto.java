package com.starbucks.back.product.dto.out;

import com.starbucks.back.product.domain.Product;
import com.starbucks.back.product.vo.out.ResponseProductVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseProductDto {

    private String productUuid;
    private String name;
    private boolean isNew;
    private boolean isBest;

    @Builder
    public ResponseProductDto(String productUuid, String name, boolean isNew, boolean isBest) {
        this.productUuid = productUuid;
        this.name = name;
        this.isNew = isNew;
        this.isBest = isBest;
    }

    public static ResponseProductDto from(Product product) {
        return ResponseProductDto.builder()
                .productUuid(product.getProductUuid())
                .name(product.getName())
                .isNew(product.isNew())
                .build();
    }

    public static ResponseProductDto of(Product product, boolean isBest) {
        return ResponseProductDto.builder()
                .productUuid(product.getProductUuid())
                .name(product.getName())
                .isNew(product.isNew())
                .isBest(isBest)
                .build();
    }

    public ResponseProductVo toVo() {
        return ResponseProductVo.builder()
                .productUuid(productUuid)
                .name(name)
                .isNew(isNew)
                .isBest(isBest)
                .build();
    }

}
