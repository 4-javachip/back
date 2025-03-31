package com.starbucks.back.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseSubCategoryVo {

    private Long id;
    private String name;
    private Long categoryId;

    @Builder
    public ResponseSubCategoryVo(Long id, String name, Long categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

}
