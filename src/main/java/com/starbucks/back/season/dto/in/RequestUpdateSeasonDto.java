package com.starbucks.back.season.dto.in;

import com.starbucks.back.season.domain.Season;
import com.starbucks.back.season.vo.in.RequestUpdateSeasonVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateSeasonDto {

    private Long id;
    private String name;

    @Builder
    public RequestUpdateSeasonDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Season updateEntity() {
        return Season.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static RequestUpdateSeasonDto from(RequestUpdateSeasonVo requestUpdateSeasonVo) {
        return RequestUpdateSeasonDto.builder()
                .id(requestUpdateSeasonVo.getId())
                .name(requestUpdateSeasonVo.getName())
                .build();
    }

}
