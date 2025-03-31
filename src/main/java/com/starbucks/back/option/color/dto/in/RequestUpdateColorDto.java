package com.starbucks.back.option.color.dto.in;

import com.starbucks.back.option.color.domain.Color;
import com.starbucks.back.option.color.vo.in.RequestColorVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateColorDto {

    private Long id;
    private String name;

    @Builder
    public RequestUpdateColorDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Color updateEntity() {
        return Color.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static RequestUpdateColorDto from(RequestColorVo requestColorVo) {
        return RequestUpdateColorDto.builder()
                .id(requestColorVo.getId())
                .name(requestColorVo.getName())
                .build();
    }

}
