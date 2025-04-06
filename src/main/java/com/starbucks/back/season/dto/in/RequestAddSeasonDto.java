package com.starbucks.back.season.dto.in;

import com.starbucks.back.season.domain.Season;
import com.starbucks.back.season.vo.in.RequestAddSeasonVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddSeasonDto {

    private String name;

    @Builder
    public RequestAddSeasonDto(String name) {
        this.name = name;
    }

    public Season toEntity() {
        return Season.builder()
                .name(name)
                .build();
    }

    public static RequestAddSeasonDto from(RequestAddSeasonVo requestAddSeasonVo) {
        return RequestAddSeasonDto.builder()
                .name(requestAddSeasonVo.getName())
                .build();
    }

}
