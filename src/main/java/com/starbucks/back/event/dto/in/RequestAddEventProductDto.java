package com.starbucks.back.event.dto.in;

import com.starbucks.back.event.domain.EventProduct;
import com.starbucks.back.event.vo.in.RequestAddEventProductVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddEventProductDto {

    private String productUuid;
    private String eventUuid;

    @Builder
    public RequestAddEventProductDto(String productUuid, String eventUuid) {
        this.productUuid = productUuid;
        this.eventUuid = eventUuid;
    }

    public EventProduct toEntity() {
        return EventProduct.builder()
                .productUuid(productUuid)
                .eventUuid(eventUuid)
                .build();
    }

    public static RequestAddEventProductDto from(RequestAddEventProductVo requestAddEventProductVo) {
        return RequestAddEventProductDto.builder()
                .productUuid(requestAddEventProductVo.getProductUuid())
                .eventUuid(requestAddEventProductVo.getEventUuid())
                .build();
    }

}
