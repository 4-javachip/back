package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.domain.User;
import com.starbucks.back.user.vo.in.RequestUpdateNicknameVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestUpdateNicknameDto {
    private String userUuid;
    private String nickname;

    @Builder
    public RequestUpdateNicknameDto(String userUuid, String nickname) {
        this.userUuid = userUuid;
        this.nickname = nickname;
    }

    public User toEntity(User user, RequestUpdateNicknameDto requestUpdateNicknameDto) {
        return User.builder()
                .id(user.getId())
                .userUuid(user.getUserUuid())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(requestUpdateNicknameDto.getNickname())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .birthdate(user.getBirthdate())
                .gender(user.getGender())
                .state(user.getState())
                .build();
    }

    public static RequestUpdateNicknameDto of(String userUuid, RequestUpdateNicknameVo requestUpdateNicknameVo) {
        return RequestUpdateNicknameDto.builder()
                .userUuid(userUuid)
                .nickname(requestUpdateNicknameVo.getNickname())
                .build();
    }
}
