package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.dto.enums.EmailVerificationPurpose;
import com.starbucks.back.user.vo.in.RequestVerificationEmailVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestVerificationEmailDto {
    private String email;
    private String verificationCode;
    private EmailVerificationPurpose purpose;

    @Builder
    public RequestVerificationEmailDto(String email, String verificationCode, EmailVerificationPurpose purpose) {
        this.email = email;
        this.verificationCode = verificationCode;
        this.purpose = purpose;
    }

    public static RequestVerificationEmailDto from(RequestVerificationEmailVo requestVerificationEmailVo) {
        return RequestVerificationEmailDto.builder()
                .email(requestVerificationEmailVo.getEmail())
                .verificationCode(requestVerificationEmailVo.getVerificationCode())
                .purpose(EmailVerificationPurpose.valueOf(requestVerificationEmailVo.getPurpose().toUpperCase()))
                .build();
    }
}
