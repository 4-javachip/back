package com.starbucks.back.option.size.dto.in;

import com.starbucks.back.option.size.vo.in.RequestDeleteSizeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteSizeDto {

    private Long id;

    @Builder
    public RequestDeleteSizeDto(Long id) {
        this.id = id;
    }

    public static RequestDeleteSizeDto of(RequestDeleteSizeVo requestDeleteSizeVo) {
        return RequestDeleteSizeDto.builder()
                .id(requestDeleteSizeVo.getId())
                .build();
    }

}
