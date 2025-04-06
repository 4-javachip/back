package com.starbucks.back.season.dto.in;

import com.starbucks.back.season.vo.in.RequestDeleteSeasonListVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteSeasonListDto {

    private Long id;

    @Builder
    public RequestDeleteSeasonListDto(Long id) {
        this.id = id;
    }

    public static RequestDeleteSeasonListDto of(RequestDeleteSeasonListVo requestDeleteSeasonListVo) {
        return RequestDeleteSeasonListDto.builder()
                .id(requestDeleteSeasonListVo.getId())
                .build();
    }

}
