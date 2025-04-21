package com.starbucks.back.auth.vo.out;

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
