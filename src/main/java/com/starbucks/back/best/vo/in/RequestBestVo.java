package com.starbucks.back.best.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestBestVo {

    private Long id;
    private String productUuid;
    private Integer productSalesCount;

}
