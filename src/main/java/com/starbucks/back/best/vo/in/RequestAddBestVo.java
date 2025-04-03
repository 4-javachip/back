package com.starbucks.back.best.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddBestVo {

    private String productUuid;
    private Integer productSalesCount;

}
