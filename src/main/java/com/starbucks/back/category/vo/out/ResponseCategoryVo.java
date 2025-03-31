package com.starbucks.back.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseCategoryVo {

    private Long id;
    private String name;
    private String image;

    @Builder
    public ResponseCategoryVo(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

}
