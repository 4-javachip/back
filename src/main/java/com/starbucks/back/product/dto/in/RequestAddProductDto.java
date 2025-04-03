package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.UUID.randomUUID;

@Getter
@NoArgsConstructor
public class RequestAddProductDto {

    private String name;

    @Builder
    public RequestAddProductDto(String name) {
        this.name = name;
    }

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .productUuid(randomUUID().toString())
                .build();
    }

}
