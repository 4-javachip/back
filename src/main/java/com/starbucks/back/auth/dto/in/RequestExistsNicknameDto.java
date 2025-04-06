package com.starbucks.back.auth.dto.in;

import com.starbucks.back.auth.vo.in.RequestExistsNicknameVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestExistsNicknameDto {
    private String nickname;

    @Builder
    public RequestExistsNicknameDto(String nickname) {
        this.nickname = nickname;
    }

    public static RequestExistsNicknameDto from(RequestExistsNicknameVo requestExistsNicknameVo) {
        return RequestExistsNicknameDto.builder()
                .nickname(requestExistsNicknameVo.getNickname())
                .build();
    }
}
