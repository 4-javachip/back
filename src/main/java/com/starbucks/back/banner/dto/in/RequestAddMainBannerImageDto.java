package com.starbucks.back.banner.dto.in;

import com.starbucks.back.banner.domain.MainBannerImage;
import com.starbucks.back.banner.vo.in.RequestAddMainBannerImageVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.UUID.randomUUID;

@Getter
@NoArgsConstructor
public class RequestAddMainBannerImageDto {

    private String eventUuid;
    private String imageUrl;
    private String description;

    @Builder
    public RequestAddMainBannerImageDto(String eventUuid, String imageUrl, String description) {
        this.eventUuid = eventUuid;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public MainBannerImage toEntity() {
        return MainBannerImage.builder()
                .mainBannerImageUuid(randomUUID().toString())
                .eventUuid(eventUuid)
                .imageUrl(imageUrl)
                .description(description)
                .build();
    }

    public static RequestAddMainBannerImageDto from(RequestAddMainBannerImageVo requestAddMainBannerImageVo) {
        return RequestAddMainBannerImageDto.builder()
                .eventUuid(requestAddMainBannerImageVo.getEventUuid())
                .imageUrl(requestAddMainBannerImageVo.getImageUrl())
                .description(requestAddMainBannerImageVo.getDescription())
                .build();
    }

}
