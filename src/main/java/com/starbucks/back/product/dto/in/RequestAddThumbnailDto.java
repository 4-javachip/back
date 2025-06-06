package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.domain.Thumbnail;
import com.starbucks.back.product.vo.in.RequestAddThumbnailVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddThumbnailDto {

    private String productUuid;
    private String thumbnailUrl;
    private String description;
    private Boolean defaulted;

    @Builder
    public RequestAddThumbnailDto(String productUuid, String thumbnailUrl, String description, Boolean defaulted) {
        this.productUuid = productUuid;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.defaulted = defaulted;
    }

    public Thumbnail toEntity() {
        return Thumbnail.builder()
                .productUuid(productUuid)
                .thumbnailUrl(thumbnailUrl)
                .description(description)
                .defaulted(defaulted)
                .build();
    }

    public static RequestAddThumbnailDto from(RequestAddThumbnailVo requestAddThumbnailVo) {
        return RequestAddThumbnailDto.builder()
                .productUuid(requestAddThumbnailVo.getProductUuid())
                .thumbnailUrl(requestAddThumbnailVo.getThumbnailUrl())
                .description(requestAddThumbnailVo.getDescription())
                .defaulted(requestAddThumbnailVo.getDefaulted())
                .build();
    }

}
