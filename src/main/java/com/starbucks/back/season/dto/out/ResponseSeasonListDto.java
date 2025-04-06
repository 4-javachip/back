package com.starbucks.back.season.dto.out;

import com.starbucks.back.season.domain.SeasonList;
import com.starbucks.back.season.vo.out.ResponseSeasonListVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSeasonListDto {

    private Long id;
    private Long seasonId;
    private String productUuid;

    @Builder
    public ResponseSeasonListDto(Long id, Long seasonId, String productUuid) {
        this.id = id;
        this.seasonId = seasonId;
        this.productUuid = productUuid;
    }

    public static ResponseSeasonListDto from(SeasonList seasonList) {
        return ResponseSeasonListDto.builder()
                .id(seasonList.getId())
                .seasonId(seasonList.getSeasonId())
                .productUuid(seasonList.getProductUuid())
                .build();
    }

    public ResponseSeasonListVo toVo() {
        return ResponseSeasonListVo.builder()
                .id(id)
                .seasonId(seasonId)
                .productUuid(productUuid)
                .build();
    }

}
