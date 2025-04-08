package com.starbucks.back.banner.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestMainBannerImageVo {

    private String mainBannerImageUuid;
    private String eventUuid;
    private String imageUrl;
    private String description;

}
