package com.starbucks.back.option.color.dto.in;

import com.starbucks.back.option.color.domain.Color;
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

}
