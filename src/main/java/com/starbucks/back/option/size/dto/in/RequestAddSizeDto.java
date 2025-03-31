package com.starbucks.back.option.size.dto.in;

import com.starbucks.back.option.size.domain.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddSizeDto {

    private String name;

    public RequestAddSizeDto(String name) {
        this.name = name;
    }

    public Size toEntity() {
        return Size.builder()
                .name(name)
                .build();
    }

}
