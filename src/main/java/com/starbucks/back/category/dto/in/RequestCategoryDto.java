package com.starbucks.back.category.dto.in;

import com.starbucks.back.category.domain.Category;
import com.starbucks.back.category.vo.in.RequestCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCategoryDto {

    private Long id;
    private String name;
    private String image;

    @Builder
    public RequestCategoryDto(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public Category toEntity() {
        return Category.builder()
                .name(name)
                .image(image)
                .build();
    }

    public Category updateEntity() {
        return Category.builder()
                .id(id)
                .name(name)
                .image(image)
                .build();
    }

    public static RequestCategoryDto from(RequestCategoryVo requestCategoryVo) {
        return RequestCategoryDto.builder()
                .id(requestCategoryVo.getId())
                .name(requestCategoryVo.getName())
                .image(requestCategoryVo.getImage())
                .build();
    }

}
