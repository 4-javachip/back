package com.starbucks.back.category.dto.in;

import com.starbucks.back.category.domain.Category;
import com.starbucks.back.category.domain.SubCategory;
import com.starbucks.back.category.vo.in.RequestCategoryVo;
import com.starbucks.back.category.vo.in.RequestSubCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestSubCategoryDto {

    private Long id;
    private String name;
    private Long categoryId;

    @Builder
    public RequestSubCategoryDto(Long id, String name, Long categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public SubCategory toEntity(Category category) {
        return SubCategory.builder()
                .name(name)
                .category(category)
                .build();
    }

    public SubCategory updateEntity(Category category) {
        return SubCategory.builder()
                .id(id)
                .name(name)
                .category(category)
                .build();
    }

    public static RequestSubCategoryDto from(RequestSubCategoryVo requestSubCategoryVo) {
        return RequestSubCategoryDto.builder()
                .id(requestSubCategoryVo.getId())
                .name(requestSubCategoryVo.getName())
                .categoryId(requestSubCategoryVo.getCategoryId())
                .build();
    }

}
