package com.starbucks.back.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseCategoryVo {

    private Long id;
    private String name;

    @Builder
    public ResponseCategoryVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
