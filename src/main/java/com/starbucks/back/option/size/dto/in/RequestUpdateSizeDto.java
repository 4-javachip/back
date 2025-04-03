package com.starbucks.back.option.size.dto.in;

import com.starbucks.back.option.size.domain.Size;
import com.starbucks.back.option.size.vo.in.RequestSizeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateSizeDto {

    private Long id;
    private String name;

    @Builder
    public RequestUpdateSizeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Size updateEntity() {
        return Size.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static RequestUpdateSizeDto from(RequestSizeVo requestSizeVo) {
        return RequestUpdateSizeDto.builder()
                .id(requestSizeVo.getId())
                .name(requestSizeVo.getName())
                .build();
    }

}
