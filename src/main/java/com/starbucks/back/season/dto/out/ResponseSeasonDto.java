package com.starbucks.back.season.dto.out;

import com.starbucks.back.season.domain.Season;
import com.starbucks.back.season.vo.out.ResponseSeasonVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSeasonDto {

    private Long id;
    private String name;

    @Builder
    public ResponseSeasonDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ResponseSeasonDto from(Season season) {
        return ResponseSeasonDto.builder()
                .id(season.getId())
                .name(season.getName())
                .build();
    }

    public ResponseSeasonVo toVo() {
        return ResponseSeasonVo.builder()
                .id(id)
                .name(name)
                .build();
    }

}
