package com.starbucks.back.product.dto.out;

import com.starbucks.back.product.domain.ProductOption;
import com.starbucks.back.product.vo.out.ResponseProductOptionVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseProductOptionDto {

    private String productOptionUuid;
    private String productUuid;
    private Long colorOptionId;
    private Long sizeOptionId;
    private Integer stock;
    private Integer price;
    private Integer discountRate;
    private Integer totalPrice;

    @Builder
    public ResponseProductOptionDto(String productOptionUuid, String productUuid,
                                 Long colorOptionId, Long sizeOptionId, Integer stock, Integer price,
                                 Integer discountRate, Integer totalPrice) {
        this.productOptionUuid = productOptionUuid;
        this.productUuid = productUuid;
        this.colorOptionId = colorOptionId;
        this.sizeOptionId = sizeOptionId;
        this.stock = stock;
        this.price = price;
        this.discountRate = discountRate;
        this.totalPrice = totalPrice;
    }

    public static ResponseProductOptionDto from(ProductOption productOption) {
        return ResponseProductOptionDto.builder()
                .productOptionUuid(productOption.getProductOptionUuid())
                .productUuid(productOption.getProductUuid())
                .colorOptionId(productOption.getColorOptionId())
                .sizeOptionId(productOption.getSizeOptionId())
                .stock(productOption.getStock())
                .price(productOption.getPrice())
                .discountRate(productOption.getDiscountRate())
                .totalPrice(productOption.getTotalPrice())
                .build();
    }

    public ResponseProductOptionVo toVo() {
        return ResponseProductOptionVo.builder()
                .productOptionUuid(productOptionUuid)
                .productUuid(productUuid)
                .colorOptionId(colorOptionId)
                .sizeOptionId(sizeOptionId)
                .stock(stock)
                .price(price)
                .discountRate(discountRate)
                .totalPrice(totalPrice)
                .build();
    }

}
