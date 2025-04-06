package com.starbucks.back.event.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseEventProductVo {

    private String productUuid;
    private String eventUuid;

    @Builder
    public ResponseEventProductVo(String productUuid, String eventUuid) {
        this.productUuid = productUuid;
        this.eventUuid = eventUuid;
    }

}
