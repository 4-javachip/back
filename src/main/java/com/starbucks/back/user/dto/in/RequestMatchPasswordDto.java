package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.dto.enums.AuthenticateCurrentPasswordPurpose;
import com.starbucks.back.user.vo.in.RequestMatchPasswordVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestMatchPasswordDto {
    private String userUuid;
    private String currentPassword;
    private AuthenticateCurrentPasswordPurpose purpose;

    @Builder
    public RequestMatchPasswordDto(String userUuid, String currentPassword, AuthenticateCurrentPasswordPurpose purpose) {
        this.userUuid = userUuid;
        this.currentPassword = currentPassword;
        this.purpose = purpose;
    }

    public static RequestMatchPasswordDto of(String userUuid, RequestMatchPasswordVo requestMatchPasswordVo) {
        return RequestMatchPasswordDto.builder()
                .userUuid(userUuid)
                .currentPassword(requestMatchPasswordVo.getCurrentPassword())
                .purpose(AuthenticateCurrentPasswordPurpose.valueOf(requestMatchPasswordVo.getPurpose().toUpperCase()))
                .build();
    }
}
