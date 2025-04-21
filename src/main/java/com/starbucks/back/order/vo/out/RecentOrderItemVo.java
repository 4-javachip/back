package com.starbucks.back.order.vo.out;

import com.starbucks.back.order.dto.out.ResponseReadOrderDetailDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RecentOrderItemVo {
    private String productUuid;
    private String name;
    private Integer quantity;
    private String thumbnail;
    private String sizeName;
    private String colorName;

    @Builder
    public RecentOrderItemVo(
            String productUuid,
            String name,
            Integer quantity,
            String thumbnail,
            String sizeName,
            String colorName
    ) {
        this.productUuid = productUuid;
        this.name = name;
        this.quantity = quantity;
        this.thumbnail = thumbnail;
        this.sizeName = sizeName;
        this.colorName = colorName;
    }

    public static RecentOrderItemVo from(ResponseReadOrderDetailDto responseReadOrderDetailDto) {
        return RecentOrderItemVo.builder()
                .productUuid(responseReadOrderDetailDto.getProductUuid())
                .name(responseReadOrderDetailDto.getName())
                .quantity(responseReadOrderDetailDto.getQuantity())
                .thumbnail(responseReadOrderDetailDto.getThumbnail())
                .sizeName(responseReadOrderDetailDto.getSizeName())
                .colorName(responseReadOrderDetailDto.getColorName())
                .build();
    }
}
