package com.starbucks.back.option.size.dto.in;

import com.starbucks.back.option.size.domain.Size;
import com.starbucks.back.option.size.vo.in.RequestAddSizeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddSizeDto {

    private String name;

    @Builder
    public RequestAddSizeDto(String name) {
        this.name = name;
    }

    public Size toEntity() {
        return Size.builder()
                .name(name)
                .build();
    }

    public static RequestAddSizeDto from(RequestAddSizeVo requestAddSizeVo) {
        return RequestAddSizeDto.builder()
                .name(requestAddSizeVo.getName())
                .build();
    }

}
