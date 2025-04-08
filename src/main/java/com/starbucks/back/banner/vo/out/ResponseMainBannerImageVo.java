package com.starbucks.back.banner.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseMainBannerImageVo {

    private String mainBannerImageUuid;
    private String eventUuid;
    private String imageUrl;
    private String description;

    @Builder
    public ResponseMainBannerImageVo(String mainBannerImageUuid, String eventUuid,
                                      String imageUrl, String description) {
        this.mainBannerImageUuid = mainBannerImageUuid;
        this.eventUuid = eventUuid;
        this.imageUrl = imageUrl;
        this.description = description;
    }

}
