package com.starbucks.back.product.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddThumbnailVo {

    private String productUuid;
    private String thumbnailUrl;
    private String description;
    private Boolean defaulted;

}
