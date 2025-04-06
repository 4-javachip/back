package com.starbucks.back.season.dto.in;

import com.starbucks.back.season.domain.SeasonList;
import com.starbucks.back.season.vo.in.RequestUpdateSeasonListVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateSeasonListDto {

    private Long id;
    private Long seasonId;
    private String productUuid;

    @Builder
    public RequestUpdateSeasonListDto(Long id, Long seasonId, String productUuid) {
        this.id = id;
        this.seasonId = seasonId;
        this.productUuid = productUuid;
    }

    public SeasonList updateEntity() {
        return SeasonList.builder()
                .id(id)
                .seasonId(seasonId)
                .productUuid(productUuid)
                .build();
    }

    public static RequestUpdateSeasonListDto from(RequestUpdateSeasonListVo requestUpdateSeasonListVo) {
        return RequestUpdateSeasonListDto.builder()
                .id(requestUpdateSeasonListVo.getId())
                .seasonId(requestUpdateSeasonListVo.getSeasonId())
                .productUuid(requestUpdateSeasonListVo.getProductUuid())
                .build();
    }

}
