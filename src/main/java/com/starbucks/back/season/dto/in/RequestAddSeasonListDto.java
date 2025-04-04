package com.starbucks.back.season.dto.in;

import com.starbucks.back.season.domain.SeasonList;
import com.starbucks.back.season.vo.in.RequestAddSeasonListVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddSeasonListDto {

    private Long seasonId;
    private String productUuid;

    @Builder
    public RequestAddSeasonListDto(Long seasonId, String productUuid) {
        this.seasonId = seasonId;
        this.productUuid = productUuid;
    }

    public SeasonList toEntity() {
        return SeasonList.builder()
                .seasonId(seasonId)
                .productUuid(productUuid)
                .build();
    }

    public static RequestAddSeasonListDto of(RequestAddSeasonListVo requestAddSeasonListVo) {
        return RequestAddSeasonListDto.builder()
                .seasonId(requestAddSeasonListVo.getSeasonId())
                .productUuid(requestAddSeasonListVo.getProductUuid())
                .build();
    }

}
