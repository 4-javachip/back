package com.starbucks.back.category.dto.in;

import com.starbucks.back.category.vo.in.RequestDeleteCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteSubCategoryDto {

    private Long id;

    @Builder
    public RequestDeleteSubCategoryDto(Long id) {
        this.id = id;
    }

    public static RequestDeleteSubCategoryDto of(RequestDeleteCategoryVo requestDeleteCategoryVo) {
        return RequestDeleteSubCategoryDto.builder()
                .id(requestDeleteCategoryVo.getId())
                .build();
    }

}
