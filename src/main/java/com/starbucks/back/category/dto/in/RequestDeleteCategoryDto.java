package com.starbucks.back.category.dto.in;

import com.starbucks.back.category.vo.in.RequestDeleteCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteCategoryDto {

    private Long id;

    @Builder
    public RequestDeleteCategoryDto(Long id) {
        this.id = id;
    }

    public static RequestDeleteCategoryDto of (RequestDeleteCategoryVo requestDeleteCategoryVo) {
        return RequestDeleteCategoryDto.builder()
                .id(requestDeleteCategoryVo.getId())
                .build();
    }

}
