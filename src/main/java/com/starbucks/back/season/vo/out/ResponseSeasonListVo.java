package com.starbucks.back.season.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSeasonListVo {

    private Long id;
    private Long seasonId;
    private String productUuid;

    @Builder
    public ResponseSeasonListVo(Long id, Long seasonId, String productUuid) {
        this.id = id;
        this.seasonId = seasonId;
        this.productUuid = productUuid;
    }

}
