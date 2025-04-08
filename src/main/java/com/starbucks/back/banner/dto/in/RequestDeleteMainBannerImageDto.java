package com.starbucks.back.banner.dto.in;

import com.starbucks.back.banner.vo.in.RequestDeleteMainBannerImageVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteMainBannerImageDto {

    private String mainBannerImageUuid;

    @Builder
    public RequestDeleteMainBannerImageDto(String mainBannerImageUuid) {
        this.mainBannerImageUuid = mainBannerImageUuid;
    }

    public static RequestDeleteMainBannerImageDto of(RequestDeleteMainBannerImageVo requestDeleteMainBannerImageVo) {
        return RequestDeleteMainBannerImageDto.builder()
                .mainBannerImageUuid(requestDeleteMainBannerImageVo.getMainBannerImageUuid())
                .build();
    }

}
