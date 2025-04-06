package com.starbucks.back.auth.dto;

import com.starbucks.back.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserAuthDto {
    private String userUuid;
    private String password;

    @Builder
    public UserAuthDto(String userUuid, String password) {
        this.userUuid = userUuid;
        this.password = password;
    }

    public static UserAuthDto from(User user) {
        return UserAuthDto.builder()
                .userUuid(user.getUserUuid())
                .password(user.getPassword())
                .build();
    }
}
