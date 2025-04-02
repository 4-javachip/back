package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.domain.Thumbnail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddThumbnailDto {

    private String productUuid;
    private String thumbnailUrl;
    private String description;

    @Builder
    public RequestAddThumbnailDto(String productUuid, String thumbnailUrl, String description) {
        this.productUuid = productUuid;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
    }

    public Thumbnail toEntity() {
        return Thumbnail.builder()
                .productUuid(productUuid)
                .thumbnailUrl(thumbnailUrl)
                .description(description)
                .build();
    }

}
