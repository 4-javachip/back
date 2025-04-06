package com.starbucks.back.product.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddProductDescriptionVo {

    private String productUuid;
    private String description;
    private String detailDescription;

}
