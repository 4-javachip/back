package com.starbucks.back.category.dto.out;

import com.starbucks.back.category.domain.Category;
import com.starbucks.back.category.vo.out.ResponseCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseCategoryDto {

    private Long id;
    private String name;
    private String image;
    private String description;

    @Builder
    public ResponseCategoryDto(Long id, String name, String image, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public static ResponseCategoryDto from(Category category) {
        return ResponseCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .image(category.getImage())
                .description(category.getDescription())
                .build();
    }

    public ResponseCategoryVo toVo() {
        return ResponseCategoryVo.builder()
                .id(id)
                .name(name)
                .image(image)
                .description(description)
                .build();
    }

}
