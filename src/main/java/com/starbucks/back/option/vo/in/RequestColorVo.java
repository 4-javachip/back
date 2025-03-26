package com.starbucks.back.option.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestColorVo {

    private Long id;
    private String name;

    @Builder
    public RequestColorVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
