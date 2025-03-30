package com.starbucks.back.option.size.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseSizeVo {

    private Long id;
    private String name;

    @Builder
    public ResponseSizeVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
