package com.starbucks.back.category.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCategoryVo {

    private Long id;
    private String name;
    private String image;
    private String description;

}
