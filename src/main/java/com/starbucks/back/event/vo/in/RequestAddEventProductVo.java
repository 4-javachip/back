package com.starbucks.back.event.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddEventProductVo {

    private String productUuid;
    private String eventUuid;

}
