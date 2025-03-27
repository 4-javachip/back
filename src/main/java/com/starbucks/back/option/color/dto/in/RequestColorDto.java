package com.starbucks.back.option.color.dto.in;

import com.starbucks.back.option.color.domain.Color;
import com.starbucks.back.option.color.vo.in.RequestColorVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestColorDto {

    private Long id;
    private String name;

    @Builder
    public RequestColorDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Color toEntity() {
        return Color.builder()
                .name(name)
                .build();
    }

    public Color updateEntity() {
        return Color.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static RequestColorDto from(RequestColorVo requestColorVo) {
        return RequestColorDto.builder()
                .id(requestColorVo.getId())
                .name(requestColorVo.getName())
                .build();
    }

}
