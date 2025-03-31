package com.starbucks.back.category.dto.out;

import com.starbucks.back.category.domain.SubCategory;
import com.starbucks.back.category.vo.out.ResponseSubCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSubCategoryDto {

    private Long id;
    private String name;
    private Long categoryId;

    @Builder
    public ResponseSubCategoryDto(Long id, String name, Long categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public static ResponseSubCategoryDto from(SubCategory subCategory) {
        return ResponseSubCategoryDto.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .categoryId(subCategory.getCategory().getId())
                .build();
    }

    public ResponseSubCategoryVo toVo() {
        return ResponseSubCategoryVo.builder()
                .id(id)
                .name(name)
                .categoryId(categoryId)
                .build();
    }

}
