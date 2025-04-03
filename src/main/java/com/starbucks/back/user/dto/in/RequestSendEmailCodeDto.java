package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.vo.in.RequestSendEmailCodeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestSendEmailCodeDto {
    private String email;

    @Builder
    public RequestSendEmailCodeDto(String email) {
        this.email = email;
    }

    public static RequestSendEmailCodeDto from(RequestSendEmailCodeVo requestSendEmailCodeVo) {
        return RequestSendEmailCodeDto.builder()
                .email(requestSendEmailCodeVo.getEmail())
                .build();
    }
}
