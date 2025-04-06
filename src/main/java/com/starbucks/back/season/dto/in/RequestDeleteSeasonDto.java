package com.starbucks.back.season.dto.in;

import com.starbucks.back.season.vo.in.RequestDeleteSeasonVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteSeasonDto {

    private Long id;

    @Builder
    public RequestDeleteSeasonDto(Long id) {
        this.id = id;
    }

    public static RequestDeleteSeasonDto of(RequestDeleteSeasonVo requestDeleteSeasonVo) {
        return RequestDeleteSeasonDto.builder()
                .id(requestDeleteSeasonVo.getId())
                .build();
    }

}
