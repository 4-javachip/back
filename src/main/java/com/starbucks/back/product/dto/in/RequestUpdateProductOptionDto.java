package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.domain.ProductOption;
import com.starbucks.back.product.vo.in.RequestProductOptionVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateProductOptionDto {

    private String productOptionUuid;
    private String productUuid;
    private Long colorOptionId;
    private Long sizeOptionId;
    private Integer stock;
    private Integer price;
    private Integer discountRate;

    @Builder
    public RequestUpdateProductOptionDto(String productOptionUuid, String productUuid,
                                         Long colorOptionId, Long sizeOptionId, Integer stock, Integer price,
                                         Integer discountRate) {
        this.productOptionUuid = productOptionUuid;
        this.productUuid = productUuid;
        this.colorOptionId = colorOptionId;
        this.sizeOptionId = sizeOptionId;
        this.stock = stock;
        this.price = price;
        this.discountRate = discountRate;
    }

    public ProductOption updateEntity(ProductOption productOption) {
        return ProductOption.builder()
                .id(productOption.getId())
                .productOptionUuid(productOptionUuid)
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

    public static RequestUpdateProductOptionDto from(RequestProductOptionVo requestProductOptionVo) {
        return RequestUpdateProductOptionDto.builder()
                .productOptionUuid(requestProductOptionVo.getProductOptionUuid())
                .productUuid(requestProductOptionVo.getProductUuid())
                .colorOptionId(requestProductOptionVo.getColorOptionId())
                .sizeOptionId(requestProductOptionVo.getSizeOptionId())
                .stock(requestProductOptionVo.getStock())
                .price(requestProductOptionVo.getPrice())
                .discountRate(requestProductOptionVo.getDiscountRate())
                .build();
    }

}
