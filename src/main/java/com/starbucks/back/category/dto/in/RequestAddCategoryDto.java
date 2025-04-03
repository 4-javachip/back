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

    @Builder
    public RequestAddCategoryDto(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Category toEntity() {
        return Category.builder()
                .name(name)
                .image(image)
                .build();
    }



}
