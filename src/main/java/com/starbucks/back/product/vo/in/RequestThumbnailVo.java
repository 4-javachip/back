package com.starbucks.back.product.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestThumbnailVo {

    private Long id;
    private String productUuid;
    private String thumbnailUrl;
    private String description;

}
