package com.starbucks.back.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseCategoryVo {

    private Long id;
    private String name;
    private String image;
    private String description;

    @Builder
    public ResponseCategoryVo(Long id, String name, String image, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
    }

}
