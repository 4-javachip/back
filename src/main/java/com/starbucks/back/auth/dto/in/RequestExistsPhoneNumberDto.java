package com.starbucks.back.auth.dto.in;

import com.starbucks.back.auth.vo.in.RequestExistsNicknameVo;
import com.starbucks.back.auth.vo.in.RequestExistsPhoneNumberVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestExistsPhoneNumberDto {
    private String phoneNumber;

    @Builder
    public RequestExistsPhoneNumberDto(String nickname) {
        this.phoneNumber = phoneNumber;
    }

    public static RequestExistsPhoneNumberDto from(
            RequestExistsPhoneNumberVo requestExistsPhoneNumberVo) {
        return RequestExistsPhoneNumberDto.builder()
                .nickname(requestExistsPhoneNumberVo.getPhoneNumber())
                .build();
    }
}
