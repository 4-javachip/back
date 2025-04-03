package com.starbucks.back.category.dto.in;

import com.starbucks.back.category.domain.Category;
import com.starbucks.back.category.vo.in.RequestCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateCategoryDto {

    private Long id;
    private String name;
    private String image;
    private String description;

    @Builder
    public RequestUpdateCategoryDto(Long id, String name, String image, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public Category updateEntity() {
        return Category.builder()
                .id(id)
                .name(name)
                .image(image)
                .description(description)
                .build();
    }

    public static RequestUpdateCategoryDto from(RequestCategoryVo requestCategoryVo) {
        return RequestUpdateCategoryDto.builder()
                .id(requestCategoryVo.getId())
                .name(requestCategoryVo.getName())
                .image(requestCategoryVo.getImage())
                .description(requestCategoryVo.getDescription())
                .build();
    }

}
