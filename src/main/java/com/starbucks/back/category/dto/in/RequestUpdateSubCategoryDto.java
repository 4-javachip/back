package com.starbucks.back.category.dto.in;

import com.starbucks.back.category.domain.Category;
import com.starbucks.back.category.domain.SubCategory;
import com.starbucks.back.category.vo.in.RequestSubCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateSubCategoryDto {

    private Long id;
    private String name;
    private Long categoryId;

    @Builder
    public RequestUpdateSubCategoryDto(Long id, String name, Long categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public SubCategory updateEntity(Category category) {
        return SubCategory.builder()
                .id(id)
                .name(name)
                .category(category)
                .build();
    }

    public static RequestUpdateSubCategoryDto from(RequestSubCategoryVo requestSubCategoryVo) {
        return RequestUpdateSubCategoryDto.builder()
                .id(requestSubCategoryVo.getId())
                .name(requestSubCategoryVo.getName())
                .categoryId(requestSubCategoryVo.getCategoryId())
                .build();
    }

}
