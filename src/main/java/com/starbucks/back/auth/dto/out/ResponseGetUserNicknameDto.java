package com.starbucks.back.auth.dto.out;

import com.starbucks.back.auth.vo.out.ResponseGetUserNicknameVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseGetUserNicknameDto {
    private String nickname;

    @Builder
    public ResponseGetUserNicknameDto(String nickname) {
        this.nickname = nickname;
    }

    public static ResponseGetUserNicknameDto from(String nickname) {
        return ResponseGetUserNicknameDto.builder()
                .nickname(nickname)
                .build();
    }

    public ResponseGetUserNicknameVo toVo() {
        return ResponseGetUserNicknameVo.builder()
                .nickname(nickname)
                .build();
    }
}
