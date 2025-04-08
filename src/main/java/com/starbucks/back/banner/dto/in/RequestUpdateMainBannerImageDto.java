package com.starbucks.back.banner.dto.in;

import com.starbucks.back.banner.domain.MainBannerImage;
import com.starbucks.back.banner.vo.in.RequestMainBannerImageVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateMainBannerImageDto {

    private String mainBannerImageUuid;
    private String eventUuid;
    private String imageUrl;
    private String description;

    @Builder
    public RequestUpdateMainBannerImageDto(String mainBannerImageUuid, String eventUuid, String imageUrl, String description) {
        this.mainBannerImageUuid = mainBannerImageUuid;
        this.eventUuid = eventUuid;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public MainBannerImage updateEntity(MainBannerImage mainBannerImage) {
        return MainBannerImage.builder()
                .id(mainBannerImage.getId())
                .mainBannerImageUuid(mainBannerImageUuid)
                .eventUuid(eventUuid)
                .imageUrl(imageUrl)
                .description(description)
                .build();
    }

    public static RequestUpdateMainBannerImageDto from(RequestMainBannerImageVo requestMainBannerImageVo) {
        return RequestUpdateMainBannerImageDto.builder()
                .mainBannerImageUuid(requestMainBannerImageVo.getMainBannerImageUuid())
                .eventUuid(requestMainBannerImageVo.getEventUuid())
                .imageUrl(requestMainBannerImageVo.getImageUrl())
                .description(requestMainBannerImageVo.getDescription())
                .build();
    }

}
