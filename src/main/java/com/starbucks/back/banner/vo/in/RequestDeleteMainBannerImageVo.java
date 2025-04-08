package com.starbucks.back.banner.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteMainBannerImageVo {

    private String mainBannerImageUuid;

    @Builder
    public RequestDeleteMainBannerImageVo(String mainBannerImageUuid) {
        this.mainBannerImageUuid = mainBannerImageUuid;
    }

}
