package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.domain.Product;
import com.starbucks.back.product.vo.in.RequestProductVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateProductDto {

    private String productUuid;
    private String name;

    @Builder
    public RequestUpdateProductDto(String productUuid, String name) {
        this.productUuid = productUuid;
        this.name = name;
    }

    public Product updateEntity(Product product) {
        return Product.builder()
                .id(product.getId())
                .productUuid(productUuid)
                .name(name)
                .build();
    }

    public static RequestUpdateProductDto from(RequestProductVo requestProductVo) {
        return RequestUpdateProductDto.builder()
                .productUuid(requestProductVo.getProductUuid())
                .name(requestProductVo.getName())
                .build();
    }

}
