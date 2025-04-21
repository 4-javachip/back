package com.starbucks.back.user.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestGetUserNicknameDto {
    private String userUuid;

    @Builder
    public RequestGetUserNicknameDto(String userUuid) {
        this.userUuid = userUuid;
    }

    public static RequestGetUserNicknameDto from(String userUuid) {
        return RequestGetUserNicknameDto.builder()
                .userUuid(userUuid)
                .build();
    }
}
