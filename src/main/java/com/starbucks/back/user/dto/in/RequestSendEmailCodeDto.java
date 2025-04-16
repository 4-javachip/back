package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.dto.enums.SendEmailPurpose;
import com.starbucks.back.user.vo.in.RequestSendEmailCodeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestSendEmailCodeDto {
    private String email;
    private SendEmailPurpose purpose;

    @Builder
    public RequestSendEmailCodeDto(String email, SendEmailPurpose purpose) {
        this.email = email;
        this.purpose = purpose;
    }

    public static RequestSendEmailCodeDto from(RequestSendEmailCodeVo requestSendEmailCodeVo) {
        return RequestSendEmailCodeDto.builder()
                .email(requestSendEmailCodeVo.getEmail())
                .purpose(SendEmailPurpose.valueOf(requestSendEmailCodeVo.getPurpose().toUpperCase()))
                .build();
    }
}
