package com.starbucks.back.season.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseSeasonVo {

    private Long id;
    private String name;

    @Builder
    public ResponseSeasonVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
