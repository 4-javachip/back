package com.starbucks.back.option.color.dto.in;

import com.starbucks.back.option.color.vo.in.RequestDeleteColorVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteColorDto {

    private Long id;

    @Builder
    public RequestDeleteColorDto(Long id) {
        this.id = id;
    }

    public static RequestDeleteColorDto of(RequestDeleteColorVo requestDeleteColorVo) {
        return RequestDeleteColorDto.builder()
                .id(requestDeleteColorVo.getId())
                .build();
    }

}
