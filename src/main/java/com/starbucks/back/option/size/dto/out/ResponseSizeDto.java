package com.starbucks.back.option.size.dto.out;

import com.starbucks.back.option.size.domain.Size;
import com.starbucks.back.option.size.vo.out.ResponseSizeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSizeDto {

    private Long id;
    private String name;

    @Builder
    public ResponseSizeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ResponseSizeDto from(Size size) {
        return ResponseSizeDto.builder()
                .id(size.getId())
                .name(size.getName())
                .build();
    }

    public ResponseSizeVo toVo() {
        return ResponseSizeVo.builder()
                .id(id)
                .name(name)
                .build();
    }

}
