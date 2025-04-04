package com.starbucks.back.category.dto.in;

import com.starbucks.back.category.domain.Category;
import com.starbucks.back.category.domain.SubCategory;
import com.starbucks.back.category.vo.in.RequestAddSubCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddSubCategoryDto {

    private String name;
    private Long categoryId;

    @Builder
    public RequestAddSubCategoryDto(String name, Long categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public SubCategory toEntity(Category category) {
        return SubCategory.builder()
                .name(name)
                .category(category)
                .build();
    }

    public static RequestAddSubCategoryDto from(RequestAddSubCategoryVo requestAddSubCategoryVo) {
        return RequestAddSubCategoryDto.builder()
                .name(requestAddSubCategoryVo.getName())
                .categoryId(requestAddSubCategoryVo.getCategoryId())
                .build();
    }

}
