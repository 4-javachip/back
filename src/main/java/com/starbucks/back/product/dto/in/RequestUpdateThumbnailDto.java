package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.domain.Thumbnail;
import com.starbucks.back.product.vo.in.RequestThumbnailVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateThumbnailDto {

    private Long id;
    private String productUuid;
    private String thumbnailUrl;
    private String description;
    private Boolean defaulted;

    @Builder
    public RequestUpdateThumbnailDto(Long id, String productUuid, String thumbnailUrl, String description, Boolean defaulted) {
        this.id = id;
        this.productUuid = productUuid;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.defaulted = defaulted;
    }

    public Thumbnail updateEntity(Thumbnail thumbnail) {
        return Thumbnail.builder()
                .id(thumbnail.getId())
                .productUuid(productUuid)
                .thumbnailUrl(thumbnailUrl)
                .description(description)
                .defaulted(defaulted)
                .build();
    }

    public static RequestUpdateThumbnailDto of(RequestThumbnailVo requestThumbnailVo) {
        return RequestUpdateThumbnailDto.builder()
                .id(requestThumbnailVo.getId())
                .productUuid(requestThumbnailVo.getProductUuid())
                .thumbnailUrl(requestThumbnailVo.getThumbnailUrl())
                .description(requestThumbnailVo.getDescription())
                .defaulted(requestThumbnailVo.getDefaulted())
                .build();
    }

}
