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

    @Builder
    public ResponseProductDto(String productUuid, String name, boolean isNew) {
        this.productUuid = productUuid;
        this.name = name;
        this.isNew = isNew;
    }

    public static ResponseProductDto from(Product product) {
        return ResponseProductDto.builder()
                .productUuid(product.getProductUuid())
                .name(product.getName())
                .isNew(product.isNew())
                .build();
    }

    public ResponseProductVo toVo() {
        return ResponseProductVo.builder()
                .productUuid(productUuid)
                .name(name)
                .isNew(isNew)
                .build();
    }

}
