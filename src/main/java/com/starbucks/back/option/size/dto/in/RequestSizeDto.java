package com.starbucks.back.option.size.dto.in;

import com.starbucks.back.option.size.domain.Size;
import com.starbucks.back.option.size.vo.in.RequestSizeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestSizeDto {

    private Long id;
    private String name;

    @Builder
    public RequestSizeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Size toEntity() {
        return Size.builder()
                .name(name)
                .build();
    }

    public Size updateEntity() {
        return Size.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static RequestSizeDto from(RequestSizeVo requestSizeVo) {
        return RequestSizeDto.builder()
                .id(requestSizeVo.getId())
                .name(requestSizeVo.getName())
                .build();
    }

}
