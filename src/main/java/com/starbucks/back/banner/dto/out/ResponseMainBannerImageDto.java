package com.starbucks.back.banner.dto.out;

import com.starbucks.back.banner.domain.MainBannerImage;
import com.starbucks.back.banner.vo.out.ResponseMainBannerImageVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseMainBannerImageDto {

    private String mainBannerImageUuid;
    private String eventUuid;
    private String imageUrl;
    private String description;

    @Builder
    public ResponseMainBannerImageDto(String mainBannerImageUuid, String eventUuid,
                                      String imageUrl, String description) {
        this.mainBannerImageUuid = mainBannerImageUuid;
        this.eventUuid = eventUuid;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public static ResponseMainBannerImageDto from(MainBannerImage mainBannerImage) {
        return ResponseMainBannerImageDto.builder()
                .mainBannerImageUuid(mainBannerImage.getMainBannerImageUuid())
                .eventUuid(mainBannerImage.getEventUuid())
                .imageUrl(mainBannerImage.getImageUrl())
                .description(mainBannerImage.getDescription())
                .build();
    }

    public ResponseMainBannerImageVo toVo() {
        return ResponseMainBannerImageVo.builder()
                .mainBannerImageUuid(mainBannerImageUuid)
                .eventUuid(eventUuid)
                .imageUrl(imageUrl)
                .description(description)
                .build();
    }

}
