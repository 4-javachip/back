package com.starbucks.back.user.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseGetUserNicknameVo {
    private String nickname;

    @Builder
    public ResponseGetUserNicknameVo(String nickname) {
        this.nickname = nickname;
    }
}
