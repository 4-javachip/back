package com.starbucks.back.season.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateSeasonListVo {

    private Long id;
    private Long seasonId;
    private String productUuid;

}
