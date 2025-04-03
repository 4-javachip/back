package com.starbucks.back.category.dto.in;

import com.starbucks.back.category.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddCategoryDto {

    private String name;
    private String image;
    private String description;

    @Builder
    public RequestAddCategoryDto(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public Category toEntity() {
        return Category.builder()
                .name(name)
                .image(image)
                .description(description)
                .build();
    }



}
