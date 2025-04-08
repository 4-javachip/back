package com.starbucks.back.banner.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddMainBannerImageVo {

    private String eventUuid;
    private String imageUrl;
    private String description;

}
