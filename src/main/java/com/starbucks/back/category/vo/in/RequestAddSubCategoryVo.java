package com.starbucks.back.category.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddSubCategoryVo {

    private String name;
    private Long categoryId;

}
