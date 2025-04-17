package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.dto.enums.SendEmailPurpose;
import com.starbucks.back.user.vo.in.RequestVerificationEmailVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestVerificationEmailDto {
    private String email;
    private String verificationCode;
    private SendEmailPurpose purpose;

    @Builder
    public RequestVerificationEmailDto(String email, String verificationCode, SendEmailPurpose purpose) {
        this.email = email;
        this.verificationCode = verificationCode;
        this.purpose = purpose;
    }

    public static RequestVerificationEmailDto from(RequestVerificationEmailVo requestVerificationEmailVo) {
        return RequestVerificationEmailDto.builder()
                .email(requestVerificationEmailVo.getEmail())
                .verificationCode(requestVerificationEmailVo.getVerificationCode())
                .purpose(SendEmailPurpose.valueOf(requestVerificationEmailVo.getPurpose().toUpperCase()))
                .build();
    }
}
