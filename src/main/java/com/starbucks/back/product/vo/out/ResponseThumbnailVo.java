package com.starbucks.back.product.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseThumbnailVo {

    private Long id;
    private String productUuid;
    private String thumbnailUrl;
    private String description;
    private Boolean defaulted;

    @Builder
    public ResponseThumbnailVo(Long id, String productUuid, String thumbnailUrl, String description, Boolean defaulted) {
        this.id = id;
        this.productUuid = productUuid;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.defaulted = defaulted;
    }

}
