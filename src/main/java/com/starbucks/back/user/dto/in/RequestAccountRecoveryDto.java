package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.vo.in.RequestAccountRecoveryVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestAccountRecoveryDto {
    private String email;

    @Builder
    public RequestAccountRecoveryDto(String email) {
        this.email = email;
    }

    public static RequestAccountRecoveryDto from(RequestAccountRecoveryVo requestAccountRecoveryVo) {
        return RequestAccountRecoveryDto.builder()
                .email(requestAccountRecoveryVo.getEmail())
                .build();
    }
}
