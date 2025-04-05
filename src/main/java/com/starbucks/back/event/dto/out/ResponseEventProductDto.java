package com.starbucks.back.event.dto.out;

import com.starbucks.back.event.domain.EventProduct;
import com.starbucks.back.event.vo.out.ResponseEventProductVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseEventProductDto {

    private String productUuid;
    private String eventUuid;

    @Builder
    public ResponseEventProductDto(String productUuid, String eventUuid) {
        this.productUuid = productUuid;
        this.eventUuid = eventUuid;
    }

    public static ResponseEventProductDto from(EventProduct eventProduct) {
        return ResponseEventProductDto.builder()
                .productUuid(eventProduct.getProductUuid())
                .eventUuid(eventProduct.getEventUuid())
                .build();
    }

    public ResponseEventProductVo toVo() {
        return ResponseEventProductVo.builder()
                .productUuid(productUuid)
                .eventUuid(eventUuid)
                .build();
    }

}
