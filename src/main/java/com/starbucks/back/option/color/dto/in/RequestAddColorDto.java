package com.starbucks.back.option.color.dto.in;

import com.starbucks.back.option.color.domain.Color;
import com.starbucks.back.option.color.vo.in.RequestAddColorVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddColorDto {

    private String name;

    @Builder
    public RequestAddColorDto(String name) {
        this.name = name;
    }

    public Color toEntity() {
        return Color.builder()
                .name(name)
                .build();
    }

    public static RequestAddColorDto from(RequestAddColorVo requestAddColorVo) {
        return RequestAddColorDto.builder()
                .name(requestAddColorVo.getName())
                .build();
    }

}
