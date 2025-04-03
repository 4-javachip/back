package com.starbucks.back.agreement.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestGetUserAgreementDto {
    private String userAgreementUuid;

    @Builder
    public RequestGetUserAgreementDto(String userAgreementUuid) {
        this.userAgreementUuid = userAgreementUuid;
    }

    public static RequestGetUserAgreementDto of(String userAgreementUuid) {
        return RequestGetUserAgreementDto.builder()
                .userAgreementUuid(userAgreementUuid)
                .build();
    }
}
