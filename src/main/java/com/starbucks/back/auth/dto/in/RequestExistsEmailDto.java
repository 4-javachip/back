package com.starbucks.back.auth.dto.in;

import com.starbucks.back.auth.vo.in.RequestExistsEmailVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestExistsEmailDto {
    private String email;

    @Builder
    public RequestExistsEmailDto(String email) {
        this.email = email;
    }

    public static RequestExistsEmailDto from(RequestExistsEmailVo requestExistsEmailVo) {
        return RequestExistsEmailDto.builder()
                .email(requestExistsEmailVo.getEmail())
                .build();
    }
}
