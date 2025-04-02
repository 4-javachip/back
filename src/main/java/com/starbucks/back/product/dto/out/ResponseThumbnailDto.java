package com.starbucks.back.product.dto.out;

import com.starbucks.back.product.domain.Thumbnail;
import com.starbucks.back.product.vo.out.ResponseThumbnailVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseThumbnailDto {

    private Long id;
    private String productUuid;
    private String thumbnailUrl;
    private String description;

    @Builder
    public ResponseThumbnailDto(Long id, String productUuid, String thumbnailUrl, String description) {
        this.id = id;
        this.productUuid = productUuid;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
    }

    public static ResponseThumbnailDto from(Thumbnail thumbnail) {
        return ResponseThumbnailDto.builder()
                .id(thumbnail.getId())
                .productUuid(thumbnail.getProductUuid())
                .thumbnailUrl(thumbnail.getThumbnailUrl())
                .description(thumbnail.getDescription())
                .build();
    }

    public ResponseThumbnailVo toVo() {
        return ResponseThumbnailVo.builder()
                .id(id)
                .productUuid(productUuid)
                .thumbnailUrl(thumbnailUrl)
                .description(description)
                .build();
    }

}
