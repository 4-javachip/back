package com.starbucks.back.option.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseColorVo {

    private Long id;
    private String name;

    @Builder
    public ResponseColorVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
