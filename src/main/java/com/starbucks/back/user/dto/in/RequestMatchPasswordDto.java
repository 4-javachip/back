package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.vo.in.RequestMatchPasswordVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestMatchPasswordDto {
    private String userUuid;
    private String currentPassword;

    @Builder
    public RequestMatchPasswordDto(String userUuid, String currentPassword) {
        this.userUuid = userUuid;
        this.currentPassword = currentPassword;
    }

    public static RequestMatchPasswordDto of(String userUuid, RequestMatchPasswordVo requestMatchPasswordVo) {
        return RequestMatchPasswordDto.builder()
                .userUuid(userUuid)
                .currentPassword(requestMatchPasswordVo.getCurrentPassword())
                .build();
    }
}
