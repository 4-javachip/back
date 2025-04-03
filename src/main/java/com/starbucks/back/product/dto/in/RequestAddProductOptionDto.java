package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.domain.ProductOption;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.UUID.randomUUID;

@Getter
@NoArgsConstructor
public class RequestAddProductOptionDto {

    private String productUuid;
    private Long colorOptionId;
    private Long sizeOptionId;
    private Integer stock;
    private Integer price;
    private Integer discountRate;

    @Builder
    public RequestAddProductOptionDto(String productUuid, Long colorOptionId, Long sizeOptionId, Integer stock, Integer price, Integer discountRate) {
        this.productUuid = productUuid;
        this.colorOptionId = colorOptionId;
        this.sizeOptionId = sizeOptionId;
        this.stock = stock;
        this.price = price;
        this.discountRate = discountRate;
    }

    public ProductOption toEntity() {
        return ProductOption.builder()
                .productOptionUuid(randomUUID().toString())
                .productUuid(productUuid)
                .colorOptionId(colorOptionId)
                .sizeOptionId(sizeOptionId)
                .stock(stock)
                .price(price)
                .discountRate(discountRate)
                .totalPrice(totalPrice())
                .build();
    }

    private int totalPrice() {
        if (discountRate == null || discountRate == 0) {
            return price;
        }
        return price - (price * discountRate / 100);
    }

}
